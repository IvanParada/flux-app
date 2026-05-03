package com.nsqws.flux.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nsqws.flux.ui.theme.AppGray
import com.nsqws.flux.R


@Composable
fun SmallInfoCard(
    icon: Int,
    title: String,
    subtitle: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(15.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = contentDescription
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    title,
                    style = typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )

                Text(
                    subtitle,
                    style = typography.bodySmall.copy(color = AppGray)
                )
            }
            Icon(
                painter = painterResource(R.drawable.arrow_right),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = contentDescription
            )
        }
    }
}