package com.nsqws.flux.features.auth.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("email")
    val email: String,

    @SerialName("password")
    val password: String,

    @SerialName("rut")
    val rut: String
)