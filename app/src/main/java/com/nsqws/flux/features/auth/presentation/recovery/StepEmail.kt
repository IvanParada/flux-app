package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.core.presentation.FluxTextField
import com.nsqws.flux.features.auth.domain.isValidEmail
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
        label = { Text("Correo Electrónico") },
        enabled = !state.isLoading,
        isError = showEmailError,
        supportingText = {
            if (showEmailError) {
                Text("Ingrese un correo válido")
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        leadingIcon  = {
            Icon(
                painter = painterResource(R.drawable.mail),
                contentDescription = "Email icon"
            )
        }
    )
    Spacer(modifier = Modifier.height(24.dp))
    FluxButton(
        onClick = onNext,
        enabled = !state.isLoading && state.email.isNotBlank() && !showEmailError,
        isLoading = state.isLoading,
        textButton = "Continuar"
    )
}