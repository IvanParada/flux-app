package com.nsqws.flux.features.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TaxPayableCard(
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography
    val appColor = MaterialTheme.colorScheme

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                "IVA por pagar",
                style = typography.bodyMedium.copy(color = appColor.secondary)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "\$541.025",
                style = typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Ventas afectas: \$2.847.500",
                style = typography.bodySmall.copy(color = appColor.secondary)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Tasa IVA: 19%",
                style = typography.bodySmall.copy(color = appColor.secondary)
            )
        }
    }
}