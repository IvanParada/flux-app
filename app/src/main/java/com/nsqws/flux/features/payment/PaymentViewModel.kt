package com.nsqws.flux.features.payment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsqws.flux.features.payment.domain.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val repository: PaymentRepository
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
        Log.d("DEBUG_PAYMENT", "1. Botón presionado. Monto: ${_state.value.amount}")
        val currentAmount = _state.value.amount

        if (currentAmount <= 0L){
            Log.d("DEBUG_PAYMENT", "2. Abortado: Monto es 0")
            return
        }

        val currentDescription = _state.value.paymentDescription.ifEmpty { "Cobro" }

        _state.update {
            it.copy(
                isLoading = true,
                error = null,
                generatedUrl = null
            )
        }
        Log.d("DEBUG_PAYMENT", "3. Estado actualizado a isLoading = true")
        viewModelScope.launch {
            Log.d("DEBUG_PAYMENT", "4. Corrutina iniciada")
            repository.createPaymentLink(
                amount = currentAmount.toInt(),
                description = currentDescription
            ).onSuccess { paymentLink ->
                Log.d("DEBUG_PAYMENT", "¡LLEGÓ! URL: ${paymentLink.url}")
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        generatedUrl = paymentLink.url
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        error = error.message ?: "Error de conexión con el servidor"
                    )
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