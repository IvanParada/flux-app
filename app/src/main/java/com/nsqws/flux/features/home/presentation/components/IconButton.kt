package com.nsqws.flux.features.home.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R

@Composable
fun IconButton(
    onClick: () -> Unit,
    icon: Int,
    backgroundColor: Color = Color.White,
    iconColor: Color = Color.Black
){
    Surface(
        modifier = Modifier.size(48.dp),
        shape = RoundedCornerShape(15.dp),
        color = backgroundColor,
        shadowElevation = 1.dp
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = iconColor
            )
        }
    }
}