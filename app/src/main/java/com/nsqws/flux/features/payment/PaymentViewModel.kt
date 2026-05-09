package com.nsqws.flux.features.payment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsqws.flux.features.payment.domain.repository.PaymentRepository
import com.nsqws.flux.features.payment.domain.usecase.ObservePaymentStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val repository: PaymentRepository,
    private val observePaymentStatusUseCase: ObservePaymentStatusUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PaymentState())
    val state = _state.asStateFlow()

    fun onDigitClick(digit: Int){
        val currentAmount = _state.value.amount

        if (currentAmount > 99_999_999L) return

        val newAmount = currentAmount * 10 + digit

        _state.value = _state.value.copy(
            amount = newAmount
        )
    }

    fun onDeleteClick(){
        val currentAmount = _state.value.amount

        _state.value = _state.value.copy(
            amount = currentAmount / 10
        )
    }

    fun onPaymentMethodSelected(method: PaymentMethodEnum){
        _state.value = state.value.copy(
            selectedPaymentMethod = method
        )
    }

    fun onAddDescriptionClick() {
        _state.value = state.value.copy(
            showDescriptionInput = true
        )
    }

    fun onDescriptionChange(value: String) {
        _state.value = state.value.copy(
            paymentDescription = value
        )
    }

    fun generatePaymentLink() {
        val currentAmount = _state.value.amount

        if (currentAmount <= 0L) return

        val currentDescription = _state.value.paymentDescription.ifEmpty { "Cobro" }

        _state.update {
            it.copy(
                isLoading = true,
                error = null,
                generatedUrl = null,
                isSuccess = false
            )
        }

        viewModelScope.launch {
            repository.createPaymentLink(
                amount = currentAmount.toInt(),
                description = currentDescription
            ).onSuccess { paymentLink ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        generatedUrl = paymentLink.url,
                        isSuccess = false
                    )
                }

                trackPaymentStatus(paymentLink.reference)

            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = error.message ?: "Error de conexión con el servidor"
                    )
                }
            }
        }
    }

    private fun trackPaymentStatus(reference: String) {
        viewModelScope.launch {
            observePaymentStatusUseCase(reference).collect { status ->
                Log.d("SOCKET_DEBUG", "Nuevo estado recibido: $status")

                when (status) {
                    "approved" -> {
                        _state.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false,
                                generatedUrl = null
                            )
                        }
                    }
                    "rejected" -> {
                        _state.update {
                            it.copy(
                                error = "Pago rechazado",
                                isSuccess = false,
                                isLoading = false
                            )
                        }
                    }
                    "in_process" -> {
                    }
                }
            }
        }
    }
    fun resetPayment() {
        _state.update { it.copy(
            generatedUrl = null,
            amount = 0L,
            paymentDescription = "",
            showDescriptionInput = false
        ) }
    }
}