package com.shubhans.pokedex

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shubhans.core.navigation.PokedexScreen

@Composable
fun PokemonNavHost(navHostController: NavHostController) {
    SharedTransitionLayout {
        NavHost(
            navController = navHostController,
            startDestination = PokedexScreen.Home,
        ) {
            pokemonNavigation()
        }
    }
}