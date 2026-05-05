package com.nsqws.flux.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.nsqws.flux.R
import com.nsqws.flux.ui.theme.AppErrorColor
import com.nsqws.flux.ui.theme.AppSuccessColor

@Composable
fun MovementItem(
    movement: MovementUi,
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography

    val movementIcon = when (movement.type) {
        MovementType.Income -> R.drawable.up_broken
        MovementType.Expense -> R.drawable.down_broken
    }

    val movementColor = when (movement.type) {
        MovementType.Income -> AppSuccessColor
        MovementType.Expense -> AppErrorColor
    }

    val movementTypeText = when (movement.type) {
        MovementType.Income -> "Ingreso"
        MovementType.Expense -> "Egreso"
    }

    ListItem(
        modifier = modifier,
        headlineContent = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = movement.title,
                        style = typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                    )

                    Text(
                        text = movement.time,
                        style = typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = movement.amount,
                        style = typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold, color = movementColor)
                    )

                    Text(
                        text = movementTypeText,
                        style = typography.bodyMedium.copy(
                            color = movementColor
                        )
                    )
                }
            }
        },
        leadingContent = {
            Icon(
                painter = painterResource(movementIcon),
                tint = movementColor,
                contentDescription = movementTypeText
            )
        }
    )
}