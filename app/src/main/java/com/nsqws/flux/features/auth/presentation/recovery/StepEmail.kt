package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.core.presentation.FluxTextField
import com.nsqws.flux.features.auth.domain.validator.isValidEmail
import com.nsqws.flux.features.auth.presentation.AuthState

@Composable
fun StepEmailContent(
    state: AuthState,
    onEmailChange: (String) -> Unit,
    onNext: () -> Unit
) {
    val showEmailError = state.email.isNotBlank() && !isValidEmail(state.email)

    Text("Ingresa tu email", style = MaterialTheme.typography.headlineMedium)
    Spacer(Modifier.height(10.dp))
    FluxTextField(
        value = state.email,
        onValueChange = onEmailChange,
        label = "Correo Electrónico",
        enabled = !state.isLoading,
        errorText = if (showEmailError) "Ingrese un correo válido" else null,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIconRes = R.drawable.mail,
        leadingIconDescription = "Email icon"
    )
    Spacer(modifier = Modifier.height(24.dp))
    FluxButton(
        onClick = onNext,
        enabled = !state.isLoading && state.email.isNotBlank() && !showEmailError,
        isLoading = state.isLoading,
        textButton = "Continuar"
    )
}