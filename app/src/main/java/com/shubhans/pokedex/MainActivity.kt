package com.shubhans.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.shubhans.core.navigation.AppComposeNavigator
import com.shubhans.core.navigation.LocalComposeNavigator
import com.shubhans.core.navigation.PokedexScreen
import com.shubhans.pokedex.ui.PokemonMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var composeNavigator: AppComposeNavigator<PokedexScreen>

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalComposeNavigator provides composeNavigator,
            ) {
                PokemonMain(
                    composeNavigator = composeNavigator
                )
            }
        }
    }
}
