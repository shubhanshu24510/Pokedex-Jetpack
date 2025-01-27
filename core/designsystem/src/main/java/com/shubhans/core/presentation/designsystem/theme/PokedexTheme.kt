@file:OptIn(ExperimentalComposeUiApi::class)

package com.shubhans.core.presentation.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

private val LocalColors = compositionLocalOf<PokedexColors> {
    error("No colors provided! Make sure to wrap all usages of Pokedex components in PokedexTheme.")
}

@Composable
public fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: PokedexColors = if (darkTheme) {
        PokedexColors.defaultDarkColors()
    } else {
        PokedexColors.defaultLightColors()
    },
    background: PokdexBackground = PokdexBackground.defaultBackground(darkTheme),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalBackgroundTheme provides background,
    ) {
        Box(
            modifier = Modifier
                .background(background.color)
                .semantics { testTagsAsResourceId = true },
        ) {
            content()
        }
    }
}

public object PokedexTheme {
    public val colors: PokedexColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    public val background: PokdexBackground
        @Composable
        @ReadOnlyComposable
        get() = LocalBackgroundTheme.current
}
