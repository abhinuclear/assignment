package com.assesment.jobfinder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define colors
val PrimaryBlue = Color(0xFF2196F3)
val PrimaryDarkBlue = Color(0xFF0069C0)
val AccentOrange = Color(0xFFFF9800)
val LightBackground = Color(0xFFF5F5F5)
val DarkBackground = Color(0xFF121212)
val LightSurface = Color(0xFFFFFFFF)
val DarkSurface = Color(0xFF1E1E1E)
val OnPrimaryDark = Color(0xFFFFFFFF)
val OnPrimaryLight = Color(0xFFFFFFFF)
val TextPrimary = Color(0xFF212121)
val TextSecondary = Color(0xFF757575)

private val DarkColorPalette = darkColors(
    primary = PrimaryBlue,
    primaryVariant = PrimaryDarkBlue,
    secondary = AccentOrange,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = OnPrimaryDark,
    onSecondary = OnPrimaryDark,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = PrimaryBlue,
    primaryVariant = PrimaryDarkBlue,
    secondary = AccentOrange,
    /* Other default colors to override */
    background = LightBackground,
    surface = LightSurface,
    onPrimary = OnPrimaryLight,
    onSecondary = Color.Black,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun JobFinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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