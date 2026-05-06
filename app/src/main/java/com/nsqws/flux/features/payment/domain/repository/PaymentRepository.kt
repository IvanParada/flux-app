package com.nsqws.flux.features.payment.domain.repository

import com.nsqws.flux.features.payment.domain.model.PaymentLink

interface PaymentRepository {
    suspend fun createPaymentLink(amount: Int, description: String): Result<PaymentLink>
}