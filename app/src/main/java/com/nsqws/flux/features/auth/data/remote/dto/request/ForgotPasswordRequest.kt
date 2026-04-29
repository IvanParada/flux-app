package com.nsqws.flux.features.auth.data.remote.dto.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordRequest(
    @SerialName("email")
    val email: String,
)
