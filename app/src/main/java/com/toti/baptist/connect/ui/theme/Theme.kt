package com.toti.baptist.connect.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2C5AA0),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD8E2FF),
    onPrimaryContainer = Color(0xFF001D60),
    secondary = Color(0xFF6B5D7F),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFEFE0FF),
    onSecondaryContainer = Color(0xFF251A3A),
    tertiary = Color(0xFF7E5260),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFD8E4),
    onTertiaryContainer = Color(0xFF31111D),
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFB0C9FF),
    onPrimary = Color(0xFF003399),
    primaryContainer = Color(0xFF1A4CAD),
    onPrimaryContainer = Color(0xFFD8E2FF),
    secondary = Color(0xFFD2C3E8),
    onSecondary = Color(0xFF3C2F52),
    secondaryContainer = Color(0xFF544B6D),
    onSecondaryContainer = Color(0xFFEFE0FF),
    tertiary = Color(0xFFFFB1C8),
    onTertiary = Color(0xFF4A2432),
    tertiaryContainer = Color(0xFF643B49),
    onTertiaryContainer = Color(0xFFFFD8E4),
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE7E0E6),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE7E0E6),
)

@Composable
fun TotiBaptistConnectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
