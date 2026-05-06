package com.nsqws.flux.features.payment.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nsqws.flux.core.utils.toClpAmount

@Composable
fun AmountDisplay(
    amount: Long,
    modifier: Modifier = Modifier,
    placeholder: String = "$0"
) {
    val isEmptyAmount = amount == 0L

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 96.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isEmptyAmount) placeholder else "$${amount.toClpAmount()}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            maxLines = 1,
            style = MaterialTheme.typography.displayLarge.copy(
                color = if (isEmptyAmount) {
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.35f)
                } else {
                    MaterialTheme.colorScheme.onPrimary
                },
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}