package com.nsqws.flux.features.profile.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.features.home.presentation.components.MovementItem
import com.nsqws.flux.features.profile.ProfileState
import com.nsqws.flux.features.profile.presentation.components.DigitalCertificateCard
import com.nsqws.flux.features.profile.presentation.components.ProfileHeader
import com.nsqws.flux.features.profile.presentation.components.SettingsCard
import com.nsqws.flux.features.profile.presentation.components.TributaryDataRow


@Composable
fun ProfileScreen(
    state: ProfileState,
    onBankAccountClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val typography = MaterialTheme.typography
    val scrollState = rememberScrollState()

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()

    ) {
        maxWidth * 0.05f
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            ProfileHeader()
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp, vertical = 12.dp)

            ){
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "DATOS TRIBUTARIOS",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)

                )

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 1.dp
                ) {
                    Column {
                        TributaryDataRow(
                            icon = R.drawable.hashtag,
                            label = "RUT Empresa",
                            value = "76.543.210-8",
                            onEditClick = { }
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(start = 70.dp),
                            thickness = 0.5.dp,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = .1f)
                        )

                        TributaryDataRow(
                            icon = R.drawable.case_icon,
                            label = "Giro Comercial",
                            value = "Servicios de Tecnología e Informática",
                            onEditClick = { }
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(start = 70.dp),
                            thickness = 0.5.dp,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = .1f)
                        )

                        TributaryDataRow(
                            icon = R.drawable.building,
                            label = "Razón Social",
                            value = "Tecno Solutions SpA",
                            onEditClick = { }
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(start = 70.dp),
                            thickness = 0.5.dp,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = .1f)
                        )

                        TributaryDataRow(
                            icon = R.drawable.building,
                            label = "Régimen Tributario",
                            value = "14D · Pyme General",
                            onEditClick = { }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "CERTIFICADO DIGITAL SII",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                )

                DigitalCertificateCard(
                    fileName = "cert_sii_2024.pfx",
                    issuer = "E-CERTCHILE",
                    isDteEnabled = true,
                    onRenewClick = { }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "CONFIGURACIÓN",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                )

                SettingsCard(
                    onBankAccountClick = onBankAccountClick,
                    onLogoutClick = onLogoutClick
                )
            }


        }
    }
}