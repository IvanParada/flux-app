package com.nsqws.flux.features.auth.di

import com.nsqws.flux.features.auth.data.repository.AuthRepository
import com.nsqws.flux.features.auth.data.repository.IAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepository
    ): IAuthRepository
}