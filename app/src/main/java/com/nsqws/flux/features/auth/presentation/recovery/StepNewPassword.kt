package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.features.auth.domain.validator.isValidPassword
import com.nsqws.flux.features.auth.presentation.AuthState
import com.nsqws.flux.core.presentation.FluxTextField


@Composable
fun StepPasswordContent(
    state: AuthState,
    onNewPasswordChange: (String) -> Unit,
    onNext: () -> Unit
) {
    val showPasswordError = state.newPassword.isNotBlank() && !isValidPassword(state.newPassword)
    var passwordVisible by remember { mutableStateOf(false) }

    Text("Ingresa tu nueva contraseña", style = MaterialTheme.typography.headlineMedium)
    Spacer(Modifier.height(10.dp))
    FluxTextField(
        value = state.newPassword,
        onValueChange = onNewPasswordChange,
        label = "Contraseña",
        enabled = !state.isLoading,
        errorText = if (showPasswordError)
            "La contraseña debe tener al menos 8 caracteres"
        else null,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIconRes = R.drawable.password,
        leadingIconDescription = "Password icon",
        isPassword = true,
        passwordVisible = passwordVisible,
        onPasswordVisibilityChange = {
            passwordVisible = !passwordVisible
        }
    )
    if (state.error != null) {
        Text(text = state.error, color = MaterialTheme.colorScheme.error)
    }
    Spacer(modifier = Modifier.height(24.dp))
    FluxButton(
        onClick = onNext,
        enabled = !state.isLoading && isValidPassword(state.newPassword),
        isLoading = state.isLoading,
        textButton = "Finalizar"
    )


}