package com.shubhans.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shubhans.core.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity WHERE page = :pages")
    suspend fun getPokemonList(pages: Int): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE page <= :pages")
    suspend fun getAllPokemonList(pages: Int): List<PokemonEntity>
}