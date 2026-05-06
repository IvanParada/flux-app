package com.nsqws.flux.features.payment.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.features.payment.PaymentMethodEnum

@Composable
fun PaymentMethodSelector(
    modifier: Modifier = Modifier,
    onPaymentMethodSelected : (PaymentMethodEnum) -> Unit,
    selectedMethod: PaymentMethodEnum
){
    Surface(
        color = MaterialTheme.colorScheme.primary.copy(alpha = .1f),
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ){
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PaymentMethodOption(
                text = "Código QR",
                iconRes = R.drawable.qr,
                selected = selectedMethod == PaymentMethodEnum.QrCode,
                onClick = {onPaymentMethodSelected(PaymentMethodEnum.QrCode)},
                modifier = Modifier.weight(1f)
            )

            PaymentMethodOption(
                text = "Link de Pago",
                iconRes = R.drawable.link,
                selected = selectedMethod == PaymentMethodEnum.PaymentLink,
                onClick = {onPaymentMethodSelected(PaymentMethodEnum.PaymentLink)},
                modifier = Modifier.weight(1f)
            )
        }
    }
}
