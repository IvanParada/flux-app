package com.nsqws.flux.features.payment.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsqws.flux.features.payment.PaymentState
import com.nsqws.flux.features.payment.presentation.components.AmountEntryHeader
import com.nsqws.flux.features.payment.presentation.components.AmountKeypad

@Composable
fun PaymentScreen(
    state: PaymentState,
    onDigitClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val horizontalPadding = maxWidth * 0.05f

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AmountEntryHeader(
                amount = state.amount,
            )
            AmountKeypad(
                onDigitClick = onDigitClick,
                onDeleteClick = onDeleteClick,
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = horizontalPadding)
            ) {
                Text("PAYMENT SCREEN")
            }
        }
    }
}