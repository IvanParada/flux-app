package com.nsqws.flux.features.auth.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsqws.flux.features.auth.presentation.AuthState
import com.nsqws.flux.features.auth.presentation.components.FluxStepIndicator

@Composable
fun RegisterScreen(
    state: AuthState,
    onRutChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onVerifyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FluxStepIndicator(
            currentStep = if (state.navigateToVerify) 1 else 0,
            totalSteps = 2
        )

        if (!state.navigateToVerify) {
            RegisterStepForm(
                state = state,
                onRutChange = onRutChange,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onRegisterClick = onRegisterClick
            )
        } else {
            RegisterStepVerify(
                state = state,
                onCodeChange = onCodeChange,
                onVerifyClick = onVerifyClick
            )
        }
        
    }
}