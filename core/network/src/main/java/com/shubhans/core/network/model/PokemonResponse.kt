package com.shubhans.core.network.model

import com.shubhans.model.Pokemon
import kotlinx.serialization.SerialName

data class PokemonResponse(
    @SerialName(value = "count") val count: Int,
    @SerialName(value = "next") val next: String?,
    @SerialName(value = "previous") val previous: String?,
    @SerialName(value = "results") val results: List<Pokemon>
)