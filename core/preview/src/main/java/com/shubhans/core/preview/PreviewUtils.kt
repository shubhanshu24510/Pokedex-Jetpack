package com.shubhans.core.preview

import com.shubhans.model.Pokemon
import com.shubhans.model.PokemonInfo

object PreviewUtils {

    fun mockPokemon() = Pokemon(
        page = 0,
        nameField = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
    )

    fun mockPokemonList() = List(10) {
        Pokemon(page = 0, nameField = "bulbasaur$it", url = "")
    }

    fun mockPokemonInfo() = PokemonInfo(
        id = 1,
        name = "bulbasaur",
        height = 7,
        weight = 69,
        experience = 60,
        types = listOf(
            PokemonInfo.TypeResponse(slot = 0, type = PokemonInfo.Type("grass")),
            PokemonInfo.TypeResponse(slot = 0, type = PokemonInfo.Type("poison")),
        ),
        stats = listOf(
            PokemonInfo.StatResponse(baseStat = 20, effort = 0, stat = PokemonInfo.Stat("hp")),
            PokemonInfo.StatResponse(baseStat = 40, effort = 0, stat = PokemonInfo.Stat("attack")),
            PokemonInfo.StatResponse(baseStat = 60, effort = 0, stat = PokemonInfo.Stat("defense")),
            PokemonInfo.StatResponse(baseStat = 80, effort = 0, stat = PokemonInfo.Stat("attack")),
        ),
    )
}