package com.shubhans.feature.details.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.kmpalette.palette.graphics.Palette
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme

@Composable
internal fun Palette?.paletteBackgroundBrush(): State<Brush> {
    val defaultBackgroundColor = PokedexTheme.colors.background
    return remember(this) {
        derivedStateOf {
            val light = this?.lightVibrantSwatch?.rgb
            val domain = this?.dominantSwatch?.rgb
            if (domain != null) {
                val domainColor = Color(domain)
                if (light != null) {
                    val lightColor = Color(light)
                    val gradient = arrayOf(
                        0.0f to domainColor,
                        1f to lightColor,
                    )
                    Brush.verticalGradient(colorStops = gradient)
                } else {
                    Brush.linearGradient(colors = listOf(domainColor, domainColor))
                }
            } else {
                Brush.linearGradient(
                    colors = listOf(
                        defaultBackgroundColor,
                        defaultBackgroundColor
                    )
                )
            }
        }
    }
}