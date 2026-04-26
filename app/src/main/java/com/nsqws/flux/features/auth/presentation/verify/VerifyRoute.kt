package com.nsqws.flux.features.auth.presentation.verify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nsqws.flux.features.auth.presentation.AuthViewModel

@Composable
fun VerifyRoute(
    email: String,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(email) {
        viewModel.onEmailChange(email)
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onNavigateToLogin()
        }
    }

    VerifyScreen(
        state = state,
        onCodeChange = viewModel::onCodeChange,
        onVerifyClick = viewModel::verify,
        modifier = modifier
    )
}