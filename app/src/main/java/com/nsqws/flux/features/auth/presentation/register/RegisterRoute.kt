package com.nsqws.flux.features.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nsqws.flux.features.auth.presentation.AuthViewModel

@Composable
fun RegisterRoute(
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) onNavigateToLogin()
    }

    RegisterScreen(
        state = state,
        onRutChange = viewModel::onRutChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onCodeChange = viewModel::onCodeChange,
        onRegisterClick = viewModel::register,
        onVerifyClick = viewModel::verify,
        onResendCode = viewModel::resendCode
    )
}