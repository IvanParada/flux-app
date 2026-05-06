package com.nsqws.flux.features.auth.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("token")
    val accessToken: String,
    @SerialName("user")
    val user: UserDto,


)

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    val rut: String
)