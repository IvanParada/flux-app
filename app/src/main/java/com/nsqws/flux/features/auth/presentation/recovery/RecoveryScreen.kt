package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            0 -> StepEmailContent(state.email, state.isLoading, onEmailChange, onNext)
            1 -> StepCodeContent(state.code, state.isLoading, onCodeChange, onNext)
            2 -> StepPasswordContent(state.password, state.isLoading, onPasswordChange, onNext)
        }
    }
}