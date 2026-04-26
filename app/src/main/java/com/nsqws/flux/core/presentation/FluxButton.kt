package com.nsqws.flux.core.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FluxButton(
    onClick: () -> Unit,
    isLoading: Boolean,
    enabled: Boolean,
    textButton: String,

){
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
    ) {
        if (isLoading)
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp).align(Alignment.CenterVertically),
                color = Color.Gray
            )
        else
            Text(textButton)

    }
}