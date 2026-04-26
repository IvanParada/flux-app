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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nsqws.flux.features.auth.presentation.AuthState
import com.nsqws.flux.features.auth.presentation.components.FluxTextFieldComponent
import com.nsqws.flux.R
import com.nsqws.flux.features.auth.presentation.utils.RutVisualTransformation
import com.nsqws.flux.features.auth.domain.isRealisticRut
import com.nsqws.flux.features.auth.domain.isValidEmail
import com.nsqws.flux.features.auth.domain.isValidPassword
import com.nsqws.flux.features.auth.presentation.utils.sanitizeRutInput

@Composable
fun RegisterScreen(
    state: AuthState,
    onRutChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val showRutError = state.rut.length >= 8 && !isRealisticRut(state.rut)
    val showEmailError = state.email.isNotBlank() && !isValidEmail(state.email)
    val showPasswordError = state.password.isNotBlank() && !isValidPassword(state.password)
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Registro", style = MaterialTheme.typography.headlineLarge)

        FluxTextFieldComponent(
            value = state.rut,
            onValueChange = { input ->
                onRutChange(
                    sanitizeRutInput(
                        input = input,
                        previous = state.rut
                    )
                )
            },
            label = { Text("RUT") },
            enabled = !state.isLoading,
            leadingIcon  = {
                Icon(
                    painter = painterResource(R.drawable.credential_user),
                    contentDescription = "RUT icon"
                )
            },
            visualTransformation = RutVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Characters
            ),
            isError = showRutError,
            supportingText = {
                if (showRutError) {
                    Text("Ingrese un RUT válido")
                }
            },
        )
        FluxTextFieldComponent(
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
        FluxTextFieldComponent(
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
            Text(text = state.error, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading &&
                    isRealisticRut(state.rut) &&
                    isValidEmail(state.email) &&
                    isValidPassword(state.password)
        ) {
            if (state.isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp))
            else Text("Registrarme")
        }
    }
}