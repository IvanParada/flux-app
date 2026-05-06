package com.nsqws.flux.features.payment

data class PaymentState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val amount: Long = 0L,
    val selectedPaymentMethod: PaymentMethodEnum = PaymentMethodEnum.QrCode,
    val showDescriptionInput: Boolean = false,
    val paymentDescription: String = ""
)
