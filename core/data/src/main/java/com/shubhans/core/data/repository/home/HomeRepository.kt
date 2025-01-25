package com.shubhans.core.data.repository.home

import com.shubhans.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>>
}