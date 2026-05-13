package com.nsqws.flux.features.profile.data.remote.api

import com.nsqws.flux.features.profile.data.remote.dto.BankConstantsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {

    @GET("payments/bank-constants")
    suspend fun getBankConstants(): Response<BankConstantsResponse>
}