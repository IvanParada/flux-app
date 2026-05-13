package com.nsqws.flux.features.profile.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.core.presentation.FluxDropdownField
import com.nsqws.flux.core.presentation.FluxTextField
import com.nsqws.flux.features.auth.domain.validator.isRealisticRut
import com.nsqws.flux.features.auth.presentation.utils.RutVisualTransformation
import com.nsqws.flux.features.auth.presentation.utils.sanitizeRutInput
import com.nsqws.flux.features.home.presentation.components.IconButton
import com.nsqws.flux.features.profile.BankAccountState
import com.nsqws.flux.features.profile.presentation.utils.isValidAccountNumberForFintocChile
import com.nsqws.flux.features.profile.presentation.utils.sanitizeAccountNumberInput

@Composable
fun BankAccountScreen(
    state: BankAccountState,
    onBackClick: () -> Unit,
    onBankSelected: (String) -> Unit,
    onAccountTypeSelected: (String) -> Unit,
    onAccountNumberChange: (String) -> Unit,
    onRutChange: (String) -> Unit,
    onSaveClick: () -> Unit

){
    val typography = MaterialTheme.typography
    val showRutError = state.rut.length >= 8 && !isRealisticRut(state.rut)
    val showAccountNumberError =
        state.accountNumber.isNotBlank() &&
                !isValidAccountNumberForFintocChile(state.accountNumber)
    val isFormValid =
        isRealisticRut(state.rut) &&
                state.selectedBankId.isNotBlank() &&
                state.selectedAccountTypeId.isNotBlank() &&
                isValidAccountNumberForFintocChile(state.accountNumber)

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
                    value = state.rut,
                    onValueChange = { input ->
                        onRutChange(
                            sanitizeRutInput(
                                input = input,
                                previous = state.rut
                            )
                        )
                    },
                    label = "RUT",
                    placeholder = "Ej: 12.345.678-9",
                    leadingIconRes = R.drawable.credential_user,
                    leadingIconDescription = "RUT icon",
                    visualTransformation = RutVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Characters
                    ),
                    errorText = if (showRutError) "Ingrese un RUT válido" else null
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                FluxDropdownField(
                    value = state.banks.find { it.id == state.selectedBankId }?.name ?: "",
                    onValueChange = { bankName ->
                        val bankId = state.banks.find { it.name == bankName }?.id ?: ""
                        onBankSelected(bankId)
                    },
                    label = "Banco",
                    placeholder = "Seleccione una opción",
                    options = state.banks.map { it.name },
                    leadingIconRes = R.drawable.building,
                    leadingIconDescription = "Banco"
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                FluxDropdownField(
                    value = state.accountTypes.find { it.id == state.selectedAccountTypeId }?.name ?: "",
                    onValueChange = { typeName ->
                        val typeId = state.accountTypes.find { it.name == typeName }?.id ?: ""
                        onAccountTypeSelected(typeId)
                    },
                    label = "Tipo de Cuenta",
                    options = state.accountTypes.map { it.name },
                    leadingIconRes = R.drawable.card,
                    leadingIconDescription = "Tipo de Cuenta"
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                FluxTextField(
                    value = state.accountNumber,
                    onValueChange = { input ->
                        onAccountNumberChange(
                            sanitizeAccountNumberInput(input)
                        )
                    },
                    label = "Número de Cuenta",
                    placeholder = "Ej: 1234567890123456",
                    leadingIconRes = R.drawable.hashtag,
                    leadingIconDescription = "Número de Cuenta",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    errorText = if (showAccountNumberError) {
                        "Ingrese un número de cuenta válido"
                    } else {
                        null
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                FluxButton(
                    onClick = onSaveClick,
                    isLoading = state.isSaving,
                    enabled = !state.isSaving && !state.isLoading && isFormValid,
                    textButton = "Guardar Cuenta Bancaria"
                )

            }
        }

    }
}