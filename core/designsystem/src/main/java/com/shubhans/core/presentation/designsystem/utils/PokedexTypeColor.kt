package com.shubhans.core.presentation.designsystem.utils

import com.shubhans.core.presentation.designsystem.theme.PokedexTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getPokemonTypeColor(type: String): Color {
    return when (type) {
        "fighting" -> PokedexTheme.colors.fighting
        "flying" -> PokedexTheme.colors.flying
        "poison" -> PokedexTheme.colors.poison
        "ground" -> PokedexTheme.colors.ground
        "rock" -> PokedexTheme.colors.rock
        "bug" -> PokedexTheme.colors.bug
        "ghost" -> PokedexTheme.colors.ghost
        "steel" -> PokedexTheme.colors.steel
        "fire" -> PokedexTheme.colors.fire
        "water" -> PokedexTheme.colors.water
        "grass" -> PokedexTheme.colors.grass
        "electric" -> PokedexTheme.colors.electric
        "psychic" -> PokedexTheme.colors.psychic
        "ice" -> PokedexTheme.colors.ice
        "dragon" -> PokedexTheme.colors.dragon
        "fairy" -> PokedexTheme.colors.fairy
        "dark" -> PokedexTheme.colors.dark
        else -> PokedexTheme.colors.gray21
    }
}