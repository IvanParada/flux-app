package com.nsqws.flux.features.home.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.features.home.HomeState
import com.nsqws.flux.features.home.presentation.components.DailyBalanceCard
import com.nsqws.flux.features.home.presentation.components.HomeHeader
import com.nsqws.flux.features.home.presentation.components.SmallInfoCard
import com.nsqws.flux.features.home.presentation.components.TaxPayableCard


@Composable
fun HomeScreen(
    state: HomeState
) {
    val typography = MaterialTheme.typography

    Scaffold { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = maxWidth * 0.05f)
            ){
                HomeHeader()
                DailyBalanceCard()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TaxPayableCard(
                        modifier = Modifier
                            .weight(1f)
                            .height(130.dp)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SmallInfoCard(
                            icon = R.drawable.document,
                            title = "12",
                            subtitle = "Boletas Emitidas",
                            contentDescription = null
                        )
                        SmallInfoCard(
                            icon = R.drawable.shield,
                            title = "SII Conectado",
                            subtitle = "Certificado activo",
                            contentDescription = null
                        )

        }
                }
                Column{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Movimientos Recientes", style = typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold))
                        TextButton(
                            onClick = {}
                        ) {
                            Text("Ver todo")
                        }
                    }
                }
            }
        }
    }
}