package com.shubhans.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shubhans.core.data.repository.details.DetailsRepository
import com.shubhans.core.presentation.viewmodel.BaseViewModel
import com.shubhans.core.presentation.viewmodel.ViewModelStateFlow
import com.shubhans.model.Pokemon
import com.shubhans.model.PokemonInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    detailsRepository: DetailsRepository, savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    internal val uiState: ViewModelStateFlow<DetailsUiState> =
        viewModelStateFlow(DetailsUiState.Loading)

    val pokemon = savedStateHandle.getStateFlow<Pokemon?>("pokemon", null)
    val pokemonInfo: StateFlow<PokemonInfo?> = pokemon.filterNotNull().flatMapLatest { pokemon ->
        detailsRepository.fetchPokemonInfo(
            name = pokemon.nameField.replaceFirstChar { it.lowercase() },
            onComplete = { uiState.tryEmit(key, DetailsUiState.Idle) },
            onError = { uiState.tryEmit(key, DetailsUiState.Error(it)) })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = null
    )
}