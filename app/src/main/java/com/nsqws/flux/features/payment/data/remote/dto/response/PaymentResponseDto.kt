package com.nsqws.flux.features.payment.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentResponseDto(
    @SerialName("url")
    val url: String,

    @SerialName("reference")
    val reference: String
)
