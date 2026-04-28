package com.nsqws.flux.features.auth.presentation

data class AuthState(
    val rut: String = "",
    val email: String = "",
    val password: String = "",
    val code: String = "",
    val newPassword: String = "",

    val recoveryStep: Int = 0,
    val resendCountdown: Int = 0,

    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isSuccess: Boolean = false,
    val isResendEnabled: Boolean = true,

    val error: String? = null,

    val navigateToVerify: Boolean = false,
    val navigateToVerifyResetCode: Boolean = false,
    val navigateToResetPassword: Boolean = false,
)

