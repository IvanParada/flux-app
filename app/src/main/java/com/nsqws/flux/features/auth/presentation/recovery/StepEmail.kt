package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StepEmailContent(email: String, isLoading: Boolean, onEmailChange: (String) -> Unit, onNext: () -> Unit) {
    Text("Paso 1: Tu Email", style = MaterialTheme.typography.titleLarge)
    OutlinedTextField(value = email, onValueChange = onEmailChange, modifier = Modifier.fillMaxWidth())
    Button(onClick = onNext, enabled = !isLoading) { Text("Siguiente") }
}