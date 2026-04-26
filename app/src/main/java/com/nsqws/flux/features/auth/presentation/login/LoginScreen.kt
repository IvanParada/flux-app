package com.nsqws.flux.features.auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsqws.flux.features.auth.presentation.AuthState

@Composable
fun LoginScreen(
    state: AuthState,
    onRutChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onNavigateToRegister: () -> Unit,
    modifier: Modifier = Modifier
) {
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

        OutlinedTextField(
            value = state.rut,
            onValueChange = onRutChange,
            label = { Text("RUT") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        )

        if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading &&
                    state.rut.isNotBlank() &&
                    state.password.isNotBlank()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Iniciar Sesión")
            }
        }

        TextButton(
            onClick = onNavigateToRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿No tienes cuenta? Registrarme")
        }
    }
}