package com.shubhans.core.network

import kotlin.annotation.AnnotationRetention.RUNTIME
import javax.inject.Qualifier

@Qualifier
@Retention(RUNTIME)
annotation class DispatcherIO(
    val pokemonAppDispatchers: PokemonAppDispatchers
)

enum class PokemonAppDispatchers {
    IO
}
