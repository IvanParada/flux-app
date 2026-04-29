package com.nsqws.flux.features.auth.data.remote.dto.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class VerifyRequest(
    @SerialName("email")
    val email: String,

    @SerialName("code")
    val code: String
)