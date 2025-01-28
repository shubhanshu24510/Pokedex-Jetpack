package com.shubhans.feature.details

import androidx.compose.runtime.Stable

@Stable
internal sealed interface DetailsUiState {
    data object Idle : DetailsUiState
    data object Loading : DetailsUiState
    data class Error(val message: String?) : DetailsUiState
}