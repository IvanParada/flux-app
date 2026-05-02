package com.nsqws.flux.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = AppBlack,
    secondary = AppGray,
    tertiary = AppBlack,

    background = AppBackground,
    surface = AppSurface,

    onPrimary = AppWhite,
    onSecondary = AppWhite,
    onTertiary = AppWhite,

    onBackground = AppBlack,
    onSurface = AppBlack,
)

@Composable
fun FluxTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}