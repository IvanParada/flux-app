package com.nsqws.flux.features.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.features.auth.presentation.AuthState
import com.nsqws.flux.core.presentation.FluxTextField
import com.nsqws.flux.features.auth.domain.isRealisticRut
import com.nsqws.flux.features.auth.domain.isValidEmail
import com.nsqws.flux.features.auth.domain.isValidPassword


@Composable
fun RegisterStepVerify(
    state: AuthState,
    onCodeChange: (String) -> Unit,
    onVerifyClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val isCodeError: Boolean = state.error?.isNotEmpty() == true

        Text(text = "Verifica tu cuenta", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "Enviamos un código a ${state.email}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        FluxTextField(
            value = state.code,
            onValueChange = onCodeChange,
            label = { Text("Código de 6 dígitos") },
            enabled = !state.isLoading,
            isError = isCodeError,
            supportingText = {
                if (isCodeError) {
                    Text("Código inválido")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            leadingIcon  = {
                Icon(
                    painter = painterResource(R.drawable.mail),
                    contentDescription = "Email icon"
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        FluxButton(
            onClick = onVerifyClick,
            enabled = !state.isLoading && state.code.length == 6,
            isLoading = state.isLoading,
            textButton = "Verificar"
        )

    }
}