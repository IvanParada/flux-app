package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsqws.flux.features.auth.presentation.AuthState
import com.nsqws.flux.features.auth.presentation.components.FluxStepIndicator

@Composable
fun RecoveryScreen(
    state: AuthState,
    onEmailChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        FluxStepIndicator(
            currentStep = state.recoveryStep,
            totalSteps = 3
        )

        Spacer(modifier = Modifier.height(32.dp))

        when (state.recoveryStep) {
            0 -> StepEmailContent(
                state = state,
                onEmailChange = onEmailChange,
                onNext = onNext
            )

            1 -> StepCodeContent(
                state = state,
                onCodeChange = onCodeChange,
                onNext = onNext
            )

            2 -> StepPasswordContent(
                state = state,
                onPassChange = onPasswordChange,
                onNext = onNext
            )
        }
    }
}