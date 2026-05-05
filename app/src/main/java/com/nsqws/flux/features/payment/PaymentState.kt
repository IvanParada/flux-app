package com.nsqws.flux.features.payment

data class PaymentState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)
