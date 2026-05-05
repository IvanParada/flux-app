package com.nsqws.flux.features.history.di

import com.nsqws.flux.features.history.data.repository.HistoryRepositoryImpl
import com.nsqws.flux.features.history.domain.repository.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HistoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HistoryRepositoryImpl

    ): HistoryRepository
}