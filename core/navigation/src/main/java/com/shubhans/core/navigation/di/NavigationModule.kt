package com.shubhans.core.navigation.di

import com.shubhans.core.navigation.AppComposeNavigator
import com.shubhans.core.navigation.PokedexComposeNavigator
import com.shubhans.core.navigation.PokedexScreen
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {
    @Binds
    @Singleton
    fun provideComposeNavigator(
        pokedexComposeNavigator: PokedexComposeNavigator,
    ): AppComposeNavigator<PokedexScreen>
}