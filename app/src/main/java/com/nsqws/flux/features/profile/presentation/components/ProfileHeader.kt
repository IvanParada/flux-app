package com.nsqws.flux.features.profile.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.ui.theme.AppSuccessColor

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    minHeight: Dp = 200.dp,
    cornerRadius: Dp = 28.dp,
) {
    val typography = MaterialTheme.typography


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
                ),

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Mi Empresa", style = typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.SemiBold))
                Surface(
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .1f),
                    shape = RoundedCornerShape(5.dp),
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                ){
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(R.drawable.pen),
                            contentDescription = null,
                            modifier = Modifier.requiredSize(15.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )

                        Text(
                            text = "Editar",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = typography.labelLarge.copy(color = MaterialTheme.colorScheme.secondary))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Surface(
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .05f),
                    shape = RoundedCornerShape(15.dp),
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                ){
                    Text(
                        text = "TS",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        style = typography.displaySmall.copy(color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold))
                }
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Column {
                    Text("Tecno Solutions SpA", modifier = Modifier.padding(vertical = 2.dp), style = typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.SemiBold))
                    Text("76.543.210-8",modifier = Modifier.padding(vertical = 4.dp), style = typography.labelLarge.copy(color = MaterialTheme.colorScheme.secondary))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text("\uD83D\uDFE2")
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Text("Cuenta Activa • Plan Pro", style = typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold, color = AppSuccessColor))
                    }
                }
            }


        }
    }
}