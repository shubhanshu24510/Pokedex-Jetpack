package com.shubhans.core.data.repository.details

import com.shubhans.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<PokemonInfo>
}