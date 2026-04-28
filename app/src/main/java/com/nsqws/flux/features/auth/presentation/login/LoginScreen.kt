package com.nsqws.flux.features.auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    val isEmailValid = isValidEmail(state.email)
    val isPasswordValid = isValidPassword(state.password)

    val showEmailError = state.email.isNotBlank() && !isEmailValid
    val showPasswordError = state.password.isNotBlank() && !isPasswordValid

    val isEnabledButton =
        !state.isLoading &&
                isEmailValid &&
                isPasswordValid

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp).imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier.height(100.dp))
        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.SemiBold)
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
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    if (isEnabledButton) {
                        onLoginClick()
                    }
                }
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
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error
            )
        }

        TextButton(
            onClick = onNavigateToRecovery,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿Olvidaste tu cuenta? Recuperarla")
        }
        Spacer(modifier = Modifier.weight(1f))

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
    }
}