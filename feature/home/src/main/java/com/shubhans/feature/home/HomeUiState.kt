package com.shubhans.feature.home

import androidx.compose.runtime.Stable

@Stable
internal sealed interface HomeUiState {
    data object Idle : HomeUiState
    data object Loading : HomeUiState
    data class Error(val message: String?) : HomeUiState
}