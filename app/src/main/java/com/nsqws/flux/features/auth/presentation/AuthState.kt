package com.nsqws.flux.features.auth.presentation

data class AuthState(
    val isLoading: Boolean = false,
    val email: String = "",
    val rut: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val navigateToVerify: Boolean = false,
    val code: String = "",
    val recoveryStep: Int = 0
)