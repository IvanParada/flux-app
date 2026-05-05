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

}