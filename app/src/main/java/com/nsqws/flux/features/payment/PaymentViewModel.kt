package com.nsqws.flux.features.payment

import androidx.lifecycle.ViewModel
import com.nsqws.flux.features.payment.domain.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

}