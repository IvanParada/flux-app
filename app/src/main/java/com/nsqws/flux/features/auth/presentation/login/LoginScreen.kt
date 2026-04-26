package com.nsqws.flux.features.auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.features.auth.domain.isValidEmail
import com.nsqws.flux.features.auth.domain.isValidPassword
import com.nsqws.flux.features.auth.presentation.AuthState
import com.nsqws.flux.core.presentation.FluxTextField

@Composable
fun LoginScreen(
    state: AuthState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToRecovery: () -> Unit,
    modifier: Modifier = Modifier
) {
    val showEmailError = state.email.isNotBlank() && !isValidEmail(state.email)
    val showPasswordError = state.password.isNotBlank() && !isValidPassword(state.password)
    val isEnabledButton = !state.isLoading && state.email.isNotBlank() && state.password.isNotBlank()
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Inicio de Sesión",
            style = MaterialTheme.typography.headlineLarge
        )

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
        FluxTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            enabled = !state.isLoading,
            isError = showPasswordError,
            supportingText = {
                if (showPasswordError) {
                    Text("La contraseña debe tener al menos 8 caracteres")
                }
            },
            visualTransformation =
                if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),

            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisible)
                                R.drawable.eye
                            else
                                R.drawable.eye_closed
                        ),
                        contentDescription = if (passwordVisible)
                            "Ocultar contraseña"
                        else
                            "Mostrar contraseña"
                    )
                }
            },
            leadingIcon  = {
                Icon(
                    painter = painterResource(R.drawable.password),
                    contentDescription = "Password icon"
                )
            }
        )

        if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error
            )
        }
        FluxButton(
            onClick = onLoginClick,
            isLoading = state.isLoading,
            enabled = isEnabledButton,
            textButton = "Iniciar Sesión"
        )

        TextButton(
            onClick = onNavigateToRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿No tienes cuenta? Registrarme")
        }
        TextButton(
            onClick = onNavigateToRecovery,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿Olvidaste tu cuenta? Recuperarla")
        }
    }
}