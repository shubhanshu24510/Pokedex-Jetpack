package com.shubhans.core.presentation.designsystem.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme

@Composable
fun BoxScope.PokemonCircularProgress() {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = PokedexTheme.colors.primary,
    )
}
