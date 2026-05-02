package com.nsqws.flux.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R

@Composable
fun HomeHeader(){
    val typography = MaterialTheme.typography

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Bienvenido de vuelta", style = typography.bodyLarge.copy(color = MaterialTheme.colorScheme.secondary))
            Text(text = "Tecno Solutions SpA", style = typography.titleLarge.copy(fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary))
        }

        Row {
            IconButton(
                onClick = {},
                icon = R.drawable.notification
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            IconButton(
                onClick = {},
                icon = R.drawable.user_hand_up,
                backgroundColor = MaterialTheme.colorScheme.primary,
                iconColor = Color.White)
        }
    }
}