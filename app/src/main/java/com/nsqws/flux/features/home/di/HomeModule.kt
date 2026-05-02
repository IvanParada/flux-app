package com.nsqws.flux.features.home.di

import com.nsqws.flux.features.home.data.repository.HomeRepositoryImpl
import com.nsqws.flux.features.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    @ViewModelScoped
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl

    ): HomeRepository
}