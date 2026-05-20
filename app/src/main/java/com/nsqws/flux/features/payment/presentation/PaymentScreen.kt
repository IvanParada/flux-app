package com.nsqws.flux.features.payment.presentation

import android.content.Intent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nsqws.flux.core.presentation.FluxButton
import com.nsqws.flux.features.payment.PaymentMethodEnum
import com.nsqws.flux.features.payment.PaymentState
import com.nsqws.flux.features.payment.presentation.components.AmountEntryHeader
import com.nsqws.flux.features.payment.presentation.components.AmountKeypad
import com.nsqws.flux.features.payment.presentation.components.PaymentMethodSelector
import com.nsqws.flux.R
import com.nsqws.flux.features.payment.presentation.components.ResultSection
import androidx.core.net.toUri

@Composable
fun PaymentScreen(
    state: PaymentState,
    onDigitClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    onPaymentMethodSelected: (PaymentMethodEnum) -> Unit,
    onAddDescriptionClick: () -> Unit,
    onDescriptionChange: (String) -> Unit,
    generatePaymentLink: () -> Unit,
    onNewPaymentLink: () -> Unit
) {

    val context = LocalContext.current

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        maxWidth * 0.05f
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AmountEntryHeader(
                amount = state.amount,
                paymentDescription = state.paymentDescription,
                showDescriptionInput = state.showDescriptionInput,
                onAddDescriptionClick = onAddDescriptionClick,
                onDescriptionChange = onDescriptionChange,
            )

            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                if (state.generatedUrl == null) {
                    AmountKeypad(
                        onDigitClick = onDigitClick,
                        onDeleteClick = onDeleteClick,
                    )

                    PaymentMethodSelector(
                        onPaymentMethodSelected = onPaymentMethodSelected,
                        selectedMethod = state.selectedPaymentMethod
                    )

                    Spacer(modifier = Modifier.padding(bottom = 15.dp))

                    if (state.error != null) {
                        Text(
                            text = state.error,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }


                    FluxButton(
                        onClick = generatePaymentLink,
                        isLoading = state.isLoading,
                        enabled = !state.isLoading && state.amount > 0,
                        textButton = if (state.selectedPaymentMethod == PaymentMethodEnum.QrCode)
                            "Generar Código QR" else "Generar Link de Pago",
                        icon = if (state.selectedPaymentMethod == PaymentMethodEnum.QrCode)
                            R.drawable.qr else R.drawable.link
                    )
                } else {
                    ResultSection(
                        state = state,
                        onShareClick = {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, state.generatedUrl)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, "Compartir link de pago")
                            context.startActivity(shareIntent)
                        },
                        onNewPaymentClick = onNewPaymentLink
                    )
                }

            }
        }
    }
}