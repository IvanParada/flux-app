package com.nsqws.flux.features.profile.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.core.presentation.FluxDropdownField
import com.nsqws.flux.core.presentation.FluxTextField
import com.nsqws.flux.features.home.presentation.components.IconButton
import com.nsqws.flux.ui.theme.AppInfoColor

@Composable
fun BankAccountScreen(
    onBackClick: () -> Unit
){
    val typography = MaterialTheme.typography

    Scaffold { innerPadding ->

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = maxWidth * 0.1f, vertical = maxHeight * 0.1f)
            ){
                IconButton(
                    onClick = onBackClick,
                    icon = R.drawable.arrow_left_circle,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    iconColor = Color.White
                )
                Row {

                    Text("Guardar cuenta bancaria", style = typography.titleLarge.copy(fontWeight = FontWeight.SemiBold), modifier = Modifier.padding(vertical = 15.dp, horizontal = 5.dp))

                }

                Text("Ingresa los datos de la cuenta donde quieres recibir el dinero de tus ventas.\n" +
                        "Las transferencias se harán automáticamente.",
                    style = typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                FluxTextField(
                    value = "",
                    onValueChange = {},
                    label = "RUT",
                    placeholder = "Ej: 12.345.678-9",
                    leadingIconRes = R.drawable.document,
                    leadingIconDescription = "RUT"
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                FluxDropdownField(
                    value = "",
                    onValueChange = {},
                    label = "Banco",
                    placeholder = "Seleccione una opción",
                    options = listOf("Banco Estado","BCI/Mach","Santander","Banco de Chile"),
                    leadingIconRes = R.drawable.building,
                    leadingIconDescription = "Banco"
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                FluxDropdownField(
                    value = "",
                    onValueChange = {},
                    label = "Tipo de Cuenta",
                    options = listOf("Corriente","Vista","Ahorro"),
                    leadingIconRes = R.drawable.card,
                    leadingIconDescription = "Tipo de Cuenta"
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                FluxTextField(
                    value = "",
                    onValueChange = {},
                    label = "Número de Cuenta",
                    placeholder = "Ej: 1234567890123456",
                    leadingIconRes = R.drawable.hashtag,
                    leadingIconDescription = "Número de Cuenta",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                FluxButton(
                    onClick = {},
                    isLoading = false,
                    enabled = true,
                    textButton = "Guardar Cuenta Bancaria"
                )

            }
        }

    }
}