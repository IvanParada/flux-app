package com.nsqws.flux.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.ui.theme.AppSuccessColor

@Composable
fun DailyBalanceCard(
){
    val typography = MaterialTheme.typography
    val textColor = MaterialTheme.colorScheme

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(15.dp),
        color = textColor.primary,
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    Text("Saldo del Día", style = typography.bodyLarge.copy(color = Color.Gray))
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text("$223.847.500", style = typography.displaySmall.copy(fontWeight = FontWeight.Bold, color = textColor.onPrimary))
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Icon(
                            painter = painterResource(R.drawable.eye),
                            tint = textColor.secondary,
                            contentDescription = null
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Badge(
                        icon = R.drawable.up_broken,
                        color = AppSuccessColor,
                        text = "+14.2% vs ayer"
                    )
                }

                Surface(
                    modifier = Modifier.wrapContentSize().padding(horizontal = 12.dp),
                    shape = RoundedCornerShape(15.dp),
                    color = textColor.onPrimary.copy(alpha = .12f)

                ){
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Text("Hoy", style = typography.bodyLarge.copy(fontWeight = FontWeight.Medium, color = textColor.secondary))
                        Text("1 Mayo", style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                        Text("2026", style = typography.bodyLarge.copy(fontWeight = FontWeight.Medium, color = textColor.secondary))
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp), color = textColor.secondary)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                BalanceMetric(
                    title = "Ingresos hoy",
                    amount = "$625.000"
                )
                VerticalDivider(modifier = Modifier.height(35.dp), color = textColor.secondary)
                BalanceMetric(
                    title = "Egresos hoy",
                    amount = "$28.900"
                )
                VerticalDivider(modifier = Modifier.height(35.dp), color = textColor.secondary)
                BalanceMetric(
                    title = "Pendiente",
                    amount = "$95.000"
                )
            }
        }
    }
}