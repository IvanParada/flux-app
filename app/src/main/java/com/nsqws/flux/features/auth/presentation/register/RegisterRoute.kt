package com.nsqws.flux.features.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nsqws.flux.features.auth.presentation.AuthViewModel

@Composable
fun RegisterRoute(
    onNavigateToVerify: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onNavigateToVerify(state.email)
        }
    }

    RegisterScreen(
        state = state,
        onRutChange = viewModel::onRutChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRegisterClick = viewModel::register,
        modifier = modifier
    )
}