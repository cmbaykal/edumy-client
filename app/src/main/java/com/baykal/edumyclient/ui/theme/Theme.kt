package com.baykal.edumyclient.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Orange,
    primaryVariant = OrangeVariant,
    secondary = Color.Black,
    background = Gray,

    )

private val LightColorPalette = lightColors(
    primary = Orange,
    primaryVariant = OrangeVariant,
    secondary = Color.Black,
    background = Color.White,
)

@Composable
fun EdumyClientTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUI = rememberSystemUiController()
    systemUI.setStatusBarColor(if (darkTheme) Gray else Color.White, !darkTheme)

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