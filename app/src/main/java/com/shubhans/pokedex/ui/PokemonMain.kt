package com.shubhans.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.shubhans.core.navigation.AppComposeNavigator
import com.shubhans.core.navigation.PokedexScreen
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme
import com.shubhans.pokedex.PokemonNavHost

@Composable
fun PokemonMain(composeNavigator: AppComposeNavigator<PokedexScreen>) {
    PokedexTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        PokemonNavHost(navHostController = navHostController)
    }
}