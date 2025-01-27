package com.shubhans.pokedex

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shubhans.core.navigation.PokedexScreen
import com.shubhans.feature.home.PokemonHome

context(SharedTransitionScope)
fun NavGraphBuilder.pokemonNavigation() {
    composable<PokedexScreen.Home> {
        PokemonHome(this)
    }
    composable<PokedexScreen.Details>(
        typeMap = PokedexScreen.Details.typeMap
    ) {

    }
}