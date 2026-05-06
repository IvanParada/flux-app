package com.nsqws.flux.features.payment.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nsqws.flux.features.payment.PaymentMethodEnum
import com.nsqws.flux.features.payment.PaymentState
import coil.compose.AsyncImage

@Composable
fun ResultSection(
    state: PaymentState,
    onShareClick: () -> Unit,
    onNewPaymentClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.selectedPaymentMethod == PaymentMethodEnum.QrCode) {
            Card(
                modifier = Modifier.size(250.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                AsyncImage(
                    model = "https://api.qrserver.com/v1/create-qr-code/?size=500x500&data=${state.generatedUrl}",
                    contentDescription = "QR Code",
                    modifier = Modifier.fillMaxSize().padding(20.dp)
                )
            }
            Text(
                text = "$${state.amount} CLP",
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text("Válido por 15 minutos", color = Color.Gray, style = MaterialTheme.typography.bodySmall)

        } else {
            // DISEÑO PARA LINK (Imagen 3)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = state.generatedUrl ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = onNewPaymentClick,
                    modifier = Modifier.weight(1f)
                ) { Text("Nuevo cobro") }

                Button(
                    onClick = onShareClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Icon(Icons.Default.Share, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Compartir")
                }
            }
        }
    }
}

