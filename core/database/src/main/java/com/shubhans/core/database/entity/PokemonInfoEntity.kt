package com.shubhans.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shubhans.model.PokemonInfo

@Entity
data class PokemonInfoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<PokemonInfo.TypeResponse>,
    val stats: List<PokemonInfo.StatResponse>,
    val exp: Int,
)
