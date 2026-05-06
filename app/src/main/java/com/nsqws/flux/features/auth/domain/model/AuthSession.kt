package com.nsqws.flux.features.auth.domain.model

data class AuthSession(
    val accessToken: String,
    val userId: String,
    val userRut: String
)