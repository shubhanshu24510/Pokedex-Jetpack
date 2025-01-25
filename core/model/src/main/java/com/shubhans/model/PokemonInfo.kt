package com.shubhans.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Immutable
@Serializable
data class PokemonInfo(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "height") val height: Int,
    @SerialName(value = "weight") val weight: Int,
    @SerialName(value = "base_experience") val experience: Int,
    @SerialName(value = "types") val types: List<TypeResponse>,
    @SerialName(value = "states") val stats: List<StatResponse>,
    val exp: Int = Random.nextInt(MAX_EXP)
) {
    val hp: Int by lazy {
        stats.firstOrNull { it.stat.name == "hp" }?.baseStat ?: Random.nextInt(MAX_HP)
    }
    val attack: Int by lazy {
        stats.firstOrNull { it.stat.name == "attack" }?.baseStat ?: Random.nextInt(MAX_ATTACK)
    }
    val defense: Int by lazy {
        stats.firstOrNull { it.stat.name == "defense" }?.baseStat ?: Random.nextInt(MAX_DEFENSE)
    }
    val speed: Int by lazy {
        stats.firstOrNull { it.stat.name == "speed" }?.baseStat ?: Random.nextInt(MAX_SPEED)
    }

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = "$hp/$MAX_HP"
    fun getAttackString(): String = "$attack/$MAX_ATTACK"
    fun getDefenseString(): String = "$defense/$MAX_DEFENSE"
    fun getSpeedString(): String = "$speed/$MAX_SPEED"
    fun getExpString(): String = "$experience/$MAX_EXP"

    @Serializable
    data class StatResponse(
        @SerialName(value = "base_stat") val baseStat: Int,
        @SerialName(value = "effort") val effort: Int,
        @SerialName(value = "stat") val stat: Stat
    )

    @Serializable
    data class Stat(
        @SerialName(value = "name") val name: String
    )

    @Serializable
    data class TypeResponse(
        @SerialName(value = "slot") val slot: Int, @SerialName(value = "type") val type: Type
    )

    @Serializable
    data class Type(
        @SerialName(value = "name") val name: String
    )

    companion object {
        const val MAX_EXP = 1000
        const val MAX_HP = 300
        const val MAX_ATTACK = 300
        const val MAX_DEFENSE = 300
        const val MAX_SPEED = 300
    }
}

