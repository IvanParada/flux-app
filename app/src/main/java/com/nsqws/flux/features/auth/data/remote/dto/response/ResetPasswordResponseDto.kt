package com.nsqws.flux.features.auth.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class ResetPasswordResponseDto(
    @SerialName("message")

    val message: String
)
