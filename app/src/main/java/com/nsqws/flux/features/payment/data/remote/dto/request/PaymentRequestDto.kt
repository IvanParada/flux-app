package com.nsqws.flux.features.payment.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequestDto(
    @SerialName("userId")
    val userId: String,

    @SerialName("amount")
    val amount: Int,

    @SerialName("description")
    val description: String
)