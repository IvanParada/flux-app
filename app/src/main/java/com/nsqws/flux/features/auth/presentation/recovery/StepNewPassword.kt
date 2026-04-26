package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun StepPasswordContent(pass: String, isLoading: Boolean, onPassChange: (String) -> Unit, onNext: () -> Unit) {
    Text("Paso 3: Nueva Clave", style = MaterialTheme.typography.titleLarge)
    OutlinedTextField(value = pass, onValueChange = onPassChange, modifier = Modifier.fillMaxWidth())
    Button(onClick = onNext, enabled = !isLoading) { Text("Finalizar") }
}