package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.features.auth.domain.isValidPassword
import com.nsqws.flux.features.auth.presentation.AuthState
import com.nsqws.flux.core.presentation.FluxTextField


@Composable
fun StepPasswordContent(
    state: AuthState,
    onPassChange: (String) -> Unit,
    onNext: () -> Unit
) {
    val showPasswordError = state.password.isNotBlank() && !isValidPassword(state.password)
    var passwordVisible by remember { mutableStateOf(false) }

    Text("Ingresa tu nueva contraseña", style = MaterialTheme.typography.headlineMedium)
    Spacer(Modifier.height(10.dp))
    FluxTextField(
        value = state.password,
        onValueChange = onPassChange,
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
    Spacer(modifier = Modifier.height(24.dp))
    FluxButton(
        onClick = onNext,
        enabled = !state.isLoading && isValidPassword(state.password),
        isLoading = state.isLoading,
        textButton = "Finalizar"
    )


}