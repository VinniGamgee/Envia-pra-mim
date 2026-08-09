package com.exemplo.mensagem.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = Color(0xFF7EC8A3),
    background = Color(0xFF0F0F0F),
    surfaceVariant = Color(0xFF2A2A2A)
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF2D5A4A),
    background = Color(0xFFF8F7F4),
    surfaceVariant = Color(0xFFE8E8E8)
)

@Composable
fun MensagemTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColors else LightColors
    MaterialTheme(colorScheme = colors, content = content)
}
