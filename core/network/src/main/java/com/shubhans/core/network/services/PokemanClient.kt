package com.shubhans.core.network.services

import com.shubhans.core.network.model.PokemonResponse
import com.shubhans.model.PokemonInfo
import com.skydoves.sandwich.ApiResponse
import jakarta.inject.Inject

class PokemonClient @Inject constructor(
    private val pokemonService: PokemonService
) {
    suspend fun fetchPokemonList(page: Int): ApiResponse<PokemonResponse> =
        pokemonService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    suspend fun fetchPokemonInfo(name: String): ApiResponse<PokemonInfo> =
        pokemonService.fetchPokemonInfo(
            name = name
        )

    companion object {
        const val PAGING_SIZE = 20
    }
}