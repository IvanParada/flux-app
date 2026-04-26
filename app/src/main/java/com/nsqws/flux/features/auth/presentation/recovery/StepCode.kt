package com.nsqws.flux.features.auth.presentation.recovery

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StepCodeContent(code: String, isLoading: Boolean, onCodeChange: (String) -> Unit, onNext: () -> Unit) {
    Text("Paso 2: Código", style = MaterialTheme.typography.titleLarge)
    OutlinedTextField(value = code, onValueChange = onCodeChange, modifier = Modifier.fillMaxWidth())
    Button(onClick = onNext, enabled = !isLoading) { Text("Verificar") }
}