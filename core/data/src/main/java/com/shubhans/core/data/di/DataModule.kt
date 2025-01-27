package com.shubhans.core.data.di

import com.shubhans.core.data.repository.details.DetailsRepository
import com.shubhans.core.data.repository.details.DetailsRepositoryImp
import com.shubhans.core.data.repository.home.HomeRepository
import com.shubhans.core.data.repository.home.HomeRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindHomeRepository(homeRepositoryImp: HomeRepositoryImp): HomeRepository

    @Binds
    fun bindDetailsRepository(detailsRepositoryImp: DetailsRepositoryImp): DetailsRepository

}