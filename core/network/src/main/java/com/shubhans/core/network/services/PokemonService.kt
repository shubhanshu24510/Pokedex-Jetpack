package com.shubhans.core.network.services

import com.shubhans.core.network.model.PokemonResponse
import com.shubhans.model.PokemonInfo
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ): ApiResponse<PokemonInfo>
}