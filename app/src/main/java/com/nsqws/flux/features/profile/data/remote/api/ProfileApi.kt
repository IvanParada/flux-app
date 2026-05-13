package com.nsqws.flux.features.profile.data.remote.api

import com.nsqws.flux.features.auth.data.remote.dto.response.MessageResponse
import com.nsqws.flux.features.profile.data.remote.dto.request.UpdateBankDataDto
import com.nsqws.flux.features.profile.data.remote.dto.response.BankConstantsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface ProfileApi {

    @GET("payments/bank-constants")
    suspend fun getBankConstants(): Response<BankConstantsResponse>

    @PATCH("payments/set-bank-data")
    suspend fun updateBankData(
        @Body updateBankDataDto: UpdateBankDataDto
    ): Response<MessageResponse>
}
