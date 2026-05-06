package com.nsqws.flux.features.payment.data.remote.api

import com.nsqws.flux.features.payment.data.remote.dto.request.PaymentRequestDto
import com.nsqws.flux.features.payment.data.remote.dto.response.PaymentResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentApi {

    @POST("payments/create-payment")
    suspend fun createPayment(
        @Body request: PaymentRequestDto
    ): Response<PaymentResponseDto>
}