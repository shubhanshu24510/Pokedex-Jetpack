package com.shubhans.core.data.repository.home

import com.shubhans.core.database.PokemonDao
import com.shubhans.core.database.mapper.asDomain
import com.shubhans.core.database.mapper.asEntity
import com.shubhans.core.network.DispatcherIO
import com.shubhans.core.network.PokemonAppDispatchers
import com.shubhans.core.network.services.PokemonClient
import com.skydoves.sandwich.onFailure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.sandwich.message
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class HomeRepositoryImp(
    private val pokemonDao: PokemonDao,
    private val pokemonClient: PokemonClient,
    @DispatcherIO(PokemonAppDispatchers.IO) private val ioDispatchers: CoroutineDispatcher
) : HomeRepository {
    override fun fetchPokemonList(
        page: Int, onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {

        var pokemons = pokemonDao.getPokemonList(page).asDomain()

        if (pokemons.isEmpty()) {
            val response = pokemonClient.fetchPokemonList(page = page)
            response.suspendOnSuccess {
                pokemons = data.results
                pokemons.forEach { pokemon ->
                    pokemon.page = page
                }
                pokemonDao.insertPokemonList(pokemons.asEntity())
                emit(pokemonDao.getAllPokemonList(page).asDomain())
            }.onFailure {
                onError(message())
            }
        } else {
            emit(pokemonDao.getAllPokemonList(page).asDomain())
        }
    }.onStart { onStart() }.onCompletion {
        onComplete()
    }.flowOn(ioDispatchers)
}