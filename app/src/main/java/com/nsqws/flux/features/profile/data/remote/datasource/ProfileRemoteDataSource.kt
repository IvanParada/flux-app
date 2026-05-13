package com.nsqws.flux.features.profile.data.remote.datasource

import com.nsqws.flux.features.auth.data.remote.dto.response.MessageResponse
import com.nsqws.flux.features.profile.data.remote.api.ProfileApi
import com.nsqws.flux.features.profile.data.remote.dto.request.UpdateBankDataDto
import com.nsqws.flux.features.profile.data.remote.dto.response.BankConstantsResponse
import retrofit2.Response
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val api: ProfileApi
){

    suspend fun getBankConstants(): Response<BankConstantsResponse> {
        return api.getBankConstants()
    }

    suspend fun updateBankData(request: UpdateBankDataDto): Response<MessageResponse> {
        return api.updateBankData(request)
    }


}