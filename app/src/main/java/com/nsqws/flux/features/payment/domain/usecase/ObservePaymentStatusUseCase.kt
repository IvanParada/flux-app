package com.nsqws.flux.features.payment.domain.usecase

import com.nsqws.flux.features.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservePaymentStatusUseCase @Inject constructor(
    private val repository: PaymentRepository
) {

    operator fun invoke(reference: String): Flow<String> {
        return repository.observeStatus(reference)
    }
}