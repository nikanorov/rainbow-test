package com.nikanorov.rainbowtestcase.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val ThemeColors = lightColors(
    primary = primaryColor,
    primaryVariant = primaryVariantColor,
    onPrimary = onPrimaryColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryVariantColor,
    onSecondary = onSecondaryColor,
    onBackground = Color.Black,
)


@Composable
fun RainbowTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content, colors = ThemeColors)
}