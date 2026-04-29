package com.nsqws.flux.features.auth.data.remote.dto.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequest(

    @SerialName("email")
    val email: String,

    @SerialName("code")
    val code: String,

    @SerialName("newPassword")
    val newPassword: String,
)