package com.shubhans.core.preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.shubhans.core.navigation.LocalComposeNavigator
import com.shubhans.core.navigation.PokedexComposeNavigator
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme

@Composable
fun PokemonPreviewTheme(
    content: @Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit,
) {
    CompositionLocalProvider(
        LocalComposeNavigator provides PokedexComposeNavigator(),
    ) {
        PokedexTheme {
            SharedTransitionScope {
                AnimatedVisibility(visible = true, label = "") {
                    content(this)
                }
            }
        }
    }
}