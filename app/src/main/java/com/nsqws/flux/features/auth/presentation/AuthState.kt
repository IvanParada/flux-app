package com.nsqws.flux.features.auth.presentation

data class AuthState(
    val isLoading: Boolean = false,
    val rut: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val errorMessage: String? = null,
    val isRegistered: Boolean = false,
    val isSuccess: Boolean = false
)