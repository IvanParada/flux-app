package com.nsqws.flux.features.profile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsqws.flux.R
import com.nsqws.flux.features.profile.ProfileState
import com.nsqws.flux.ui.theme.AppCianColor
import com.nsqws.flux.ui.theme.AppErrorColor
import com.nsqws.flux.ui.theme.AppInfoColor
import com.nsqws.flux.ui.theme.AppPurpleColor
import com.nsqws.flux.ui.theme.AppSuccessColor
import com.nsqws.flux.ui.theme.AppWarningColor

@Composable
fun SettingsCard(
    onBankAccountClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 1.dp
    ) {
        Column {
            SettingsRow(
                icon = R.drawable.notification,
                title = "Notificaciones",
                subtitle = "Cobros, boletas y alertas",
                iconBackground = AppCianColor.copy(alpha = .1f),
                iconTint = AppCianColor,
                onClick = { }

            )

            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
            )

            SettingsRow(
                icon = R.drawable.shield,
                title = "Seguridad",
                subtitle = "PIN, biometría, 2FA",
                iconBackground = AppPurpleColor.copy(alpha = .1f),
                iconTint = AppPurpleColor,
                onClick = { }
            )

            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
            )

            SettingsRow(
                icon = R.drawable.bank_account,
                title = "Cuenta Bancaria",
                subtitle = "Cuenta para recibir tus pagos",
                iconBackground = AppSuccessColor.copy(alpha = .1f),
                iconTint = AppSuccessColor,
                onClick = onBankAccountClick
            )

            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
            )

            SettingsRow(
                icon = R.drawable.card,
                title = "Plan & Suscripción",
                subtitle = "Plan Pro · $9.990/mes",
                iconBackground = AppInfoColor.copy(alpha = .1f),
                iconTint = AppInfoColor,
                badge = "Pro",
                onClick = { }
            )

            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
            )

            SettingsRow(
                icon = R.drawable.question,
                title = "Ayuda & Soporte",
                subtitle = "Chat, docs, videotutoriales",
                iconBackground = AppWarningColor.copy(alpha = .1f),
                iconTint = AppWarningColor,
                onClick = { }
            )

            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
            )

            SettingsRow(
                icon = R.drawable.exit,
                title = "Cerrar sesión",
                subtitle = null,
                iconBackground = AppErrorColor.copy(alpha = .1f),
                iconTint = AppErrorColor,
                titleColor = AppErrorColor,
                onClick = onLogoutClick
            )
        }
    }
}