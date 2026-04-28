package com.nsqws.flux.features.auth.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nsqws.flux.features.auth.presentation.AuthState
import com.nsqws.flux.core.presentation.FluxTextField
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.features.auth.presentation.utils.RutVisualTransformation
import com.nsqws.flux.features.auth.domain.isRealisticRut
import com.nsqws.flux.features.auth.domain.isValidEmail
import com.nsqws.flux.features.auth.domain.isValidPassword
import com.nsqws.flux.features.auth.presentation.utils.sanitizeRutInput

@Composable
fun RegisterStepForm(
    state: AuthState,
    onRutChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    val showRutError = state.rut.length >= 8 && !isRealisticRut(state.rut)
    val showEmailError = state.email.isNotBlank() && !isValidEmail(state.email)
    val showPasswordError = state.password.isNotBlank() && !isValidPassword(state.password)
    var passwordVisible by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Registro", style = MaterialTheme.typography.headlineLarge)
        FluxTextField(
            value = state.rut,
            onValueChange = { input ->
                onRutChange(
                    sanitizeRutInput(
                        input = input,
                        previous = state.rut
                    )
                )
            },
            label = "RUT",
            enabled = !state.isLoading,
            leadingIconRes = R.drawable.credential_user,
            leadingIconDescription = "RUT icon",
            visualTransformation = RutVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Characters
            ),
            errorText = if (showRutError) "Ingrese un RUT válido" else null
        )
        FluxTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = "Correo Electrónico",
            enabled = !state.isLoading,
            errorText = if (showEmailError) "Ingrese un correo válido" else null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            leadingIconRes = R.drawable.mail,
            leadingIconDescription = "Email icon"
        )
        FluxTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = "Contraseña",
            enabled = !state.isLoading,
            errorText = if (showPasswordError)
                "La contraseña debe tener al menos 8 caracteres"
            else null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
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
            onClick = onRegisterClick,
            enabled = !state.isLoading &&
                    isRealisticRut(state.rut) &&
                    isValidEmail(state.email) &&
                    isValidPassword(state.password),
            isLoading = state.isLoading,
            textButton = "Registrarme"
        )
    }
}