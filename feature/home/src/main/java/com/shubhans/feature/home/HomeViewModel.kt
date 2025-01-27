package com.shubhans.feature.home

import androidx.lifecycle.viewModelScope
import com.shubhans.core.data.repository.home.HomeRepository
import com.shubhans.core.presentation.viewmodel.BaseViewModel
import com.shubhans.core.presentation.viewmodel.ViewModelStateFlow
import com.shubhans.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {
    internal val uiState: ViewModelStateFlow<HomeUiState> = viewModelStateFlow(HomeUiState.Loading)

    private val pokemonFetchIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    val pokemonList: StateFlow<List<Pokemon>> = pokemonFetchIndex.flatMapLatest { page ->
        homeRepository.fetchPokemonList(
            page = page,
            onStart = { uiState.tryEmit(key, HomeUiState.Loading) },
            onComplete = { uiState.tryEmit(key, HomeUiState.Idle) },
            onError = { uiState.tryEmit(key, HomeUiState.Error(it)) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = emptyList()
    )

    fun fetchPokemonList() {
        if (uiState.value != HomeUiState.Loading) {
            pokemonFetchIndex.value++
        }
    }
}