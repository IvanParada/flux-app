package com.nsqws.flux.features.payment.di

import com.nsqws.flux.features.payment.data.repository.PaymentRepositoryImpl
import com.nsqws.flux.features.payment.domain.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class PaymentModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPaymentRepository(
        paymentRepositoryImpl: PaymentRepositoryImpl
    ): PaymentRepository
}