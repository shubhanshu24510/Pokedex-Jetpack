package com.shubhans.core.presentation.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shubhans.core.presentation.designsystem.R

@Immutable
public data class PokdexBackground(
    val color: Color = Color.Unspecified,
    val tonalElevation: Dp = Dp.Unspecified,
) {
    public companion object {
        @Composable
        public fun defaultBackground(darkTheme: Boolean): PokdexBackground {
            return if (darkTheme) {
                PokdexBackground(
                    color = colorResource(id = R.color.background_dark),
                    tonalElevation = 0.dp,
                )
            } else {
                PokdexBackground(
                    color = colorResource(id = R.color.background),
                    tonalElevation = 0.dp,
                )
            }
        }
    }
}

public val LocalBackgroundTheme: ProvidableCompositionLocal<PokdexBackground> =
    staticCompositionLocalOf { PokdexBackground() }


