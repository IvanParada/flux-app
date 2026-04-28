package com.nsqws.flux.features.auth.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyResetCodeRequest(

    @SerialName("email")
    val email: String,

    @SerialName("code")
    val code: String
)