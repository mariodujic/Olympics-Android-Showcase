package com.zero.olympics.features.common.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DeepBlue,
    secondary = OrangeYellow1,
    surface = DeepBlue
)

private val LightColorPalette = lightColors(
    primary = LightGreen2,
    primaryVariant = BlueViolet3,
    secondary = LightGreen2
)

@Composable
fun OlympicsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}