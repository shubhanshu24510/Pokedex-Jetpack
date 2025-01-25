package com.shubhans.core.database.di

import android.app.Application
import androidx.room.Room
import com.shubhans.core.database.PokemonDao
import com.shubhans.core.database.PokemonDatabase
import com.shubhans.core.database.StatsResponseConverter
import com.shubhans.core.database.TypeResponseConverter
import com.shubhans.core.database.entity.PokemonInfoEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        statsResponseConverter: StatsResponseConverter,
        typeResponseConverter: TypeResponseConverter
    ): PokemonDatabase {
        return Room.databaseBuilder(
            application,
            PokemonDatabase::class.java,
            "pokemon_db"
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(statsResponseConverter)
            .addTypeConverter(typeResponseConverter)
            .build(
            )
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: PokemonDatabase): PokemonDao {
        return appDatabase.pokemonDao()
    }

    @Provides
    @Singleton
    fun providePokemonInfoDao(appDatabase: PokemonDatabase): PokemonInfoEntity {
        return appDatabase.pokemonInfoDao()
    }

    @Provides
    @Singleton
    fun provideStatsResponseConverter(json: Json): StatsResponseConverter {
        return StatsResponseConverter(json)
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(json: Json): TypeResponseConverter {
        return TypeResponseConverter(json)
    }
}