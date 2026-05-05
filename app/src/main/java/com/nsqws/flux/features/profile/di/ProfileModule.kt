package com.nsqws.flux.features.profile.di

import com.nsqws.flux.features.profile.data.repository.ProfileRepositoryImpl
import com.nsqws.flux.features.profile.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileModule {

    @Binds
    @ViewModelScoped
    abstract fun bindHomeRepository(
        homeRepositoryImpl: ProfileRepositoryImpl

    ): ProfileRepository
}