package com.nsqws.flux.features.profile.domain.repository

import com.nsqws.flux.features.profile.data.remote.dto.response.BankConstantsResponse

interface ProfileRepository {
    suspend fun getBankConstants(): Result<BankConstantsResponse>
    suspend fun updateBankData(
        bankHolderId: String,
        bankNumber: String,
        bankType: String,
        bankInstitutionId: String
    ): Result<Unit>
}