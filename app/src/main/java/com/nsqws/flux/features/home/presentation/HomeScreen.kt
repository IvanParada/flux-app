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
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(130.dp),
                        shape = RoundedCornerShape(15.dp),
                        color = Color.White,
                        shadowElevation = 1.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                        ) {
                            Column {
                                Text(
                                    "IVA por pagar",
                                    style = typography.bodyMedium.copy(color = Color.Gray)
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "\$541.025",
                                    style = typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "Ventas afectas: \$2.847.500",
                                    style = typography.bodySmall.copy(color = Color.Gray)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    "Tasa IVA: 19%",
                                    style = typography.bodySmall.copy(color = Color.Gray)
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            shape = RoundedCornerShape(15.dp),
                            color = Color.White,
                            shadowElevation = 1.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.document),
                                    contentDescription = "Tickets"
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Column {
                                    Text(
                                        "12",
                                        style = typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                                    )

                                    Text(
                                        "Boletas Emitidas",
                                        style = typography.bodySmall.copy(color = Color.Gray)
                                    )
                                }
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            shape = RoundedCornerShape(15.dp),
                            color = Color.White,
                            shadowElevation = 1.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.shield),
                                    contentDescription = "Connected SII"
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Column {
                                    Text(
                                        "SII Conectado",
                                        style = typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                                    )

                                    Text(
                                        "Certificado activo",
                                        style = typography.bodySmall.copy(color = Color.Gray)
                                    )
                                }
                            }
                        }
                    }
                }

            }


        }
    }
}