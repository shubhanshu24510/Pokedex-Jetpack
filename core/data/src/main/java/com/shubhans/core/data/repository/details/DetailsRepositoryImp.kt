package com.shubhans.core.data.repository.details

import com.shubhans.core.database.PokemonInfoDao
import com.shubhans.core.database.mapper.asDomain
import com.shubhans.core.database.mapper.asEntity
import com.shubhans.core.network.DispatcherIO
import com.shubhans.core.network.PokemonAppDispatchers
import com.shubhans.core.network.mapper.ErrorResponseMapper
import com.shubhans.core.network.services.PokemonClient
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion

class DetailsRepositoryImp(
    private val pokemonInfoDao: PokemonInfoDao,
    private val pokemonClient: PokemonClient,
    @DispatcherIO(PokemonAppDispatchers.IO) private val ioDispatchers: CoroutineDispatcher
) : DetailsRepository {
    override fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
        if (pokemonInfo == null) {
            val response = pokemonClient.fetchPokemonInfo(name)
            response.suspendOnSuccess {
                pokemonInfoDao.insertPokemonInfo(data.asEntity())
                emit(data)
            }.onError {
                map(ErrorResponseMapper) {
                    onError("[Code: $code]: $message")
                }
            }.onException { onError(message) }
        } else {
            emit(pokemonInfo.asDomain())
        }
    }.onCompletion {
        onComplete()
    }.flowOn(ioDispatchers)
}