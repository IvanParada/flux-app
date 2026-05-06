package com.nsqws.flux.features.payment.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AmountEntryHeader(
    modifier: Modifier = Modifier,
    minHeight: Dp = 260.dp,
    cornerRadius: Dp = 28.dp,
    amount: Long,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight),
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(
            bottomStart = cornerRadius,
            bottomEnd = cornerRadius
        ),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    horizontal = 24.dp,
                    vertical = 20.dp
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Column {
                    Text(
                        text = "Cobrar a cliente",
                        style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.secondary)
                    )
                    Text(
                        text = "Cobro Rápido",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
                Surface(
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .1f),
                    shape = RoundedCornerShape(5.dp),
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                ){
                    Text(
                        text = "CLP $",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.secondary))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            AmountDisplay(
                amount = amount,
                modifier = Modifier.fillMaxWidth()
            )


        }
    }
}