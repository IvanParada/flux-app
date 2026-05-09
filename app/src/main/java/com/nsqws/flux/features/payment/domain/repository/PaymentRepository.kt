package com.nsqws.flux.features.payment.domain.repository

import com.nsqws.flux.features.payment.domain.model.PaymentLink
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    suspend fun createPaymentLink(amount: Int, description: String): Result<PaymentLink>
    fun observeStatus(reference: String): Flow<String>
}