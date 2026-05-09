package com.nsqws.flux.features.payment.di

import com.nsqws.flux.features.payment.data.remote.datasource.SocketPaymentDataSource
import com.nsqws.flux.features.payment.data.repository.PaymentRepositoryImpl
import com.nsqws.flux.features.payment.domain.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
abstract class PaymentModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPaymentRepository(
        paymentRepositoryImpl: PaymentRepositoryImpl
    ): PaymentRepository

    companion object {
        @Provides
        @ViewModelScoped
        @Named("baseUrl")
        fun provideBaseUrl(): String {
            return "https://dominica-courtly-darcy.ngrok-free.dev"
        }

        @Provides
        @ViewModelScoped
        fun provideSocketPaymentDataSource(
            @Named("baseUrl") baseUrl: String
        ): SocketPaymentDataSource {
            return SocketPaymentDataSource(baseUrl)
        }
    }
}