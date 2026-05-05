package com.nsqws.flux.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class MovementUi(
    val title: String,
    val time: String,
    val amount: String,
    val type: MovementType
)

enum class MovementType {
    Income,
    Expense
}

@Composable
fun RecentMovementsSection(
    movements: List<MovementUi>,
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit = {}
) {
    val typography = MaterialTheme.typography

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Movimientos Recientes",
                style = typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )

            TextButton(
                onClick = onSeeAllClick
            ) {
                Text("Ver todo")
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 1.dp
        ) {
            Column {
                movements.forEachIndexed { index, movement ->
                    MovementItem(movement = movement)

                    if (index < movements.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}