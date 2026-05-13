package com.nsqws.flux.features.profile.domain.repository

import com.nsqws.flux.features.profile.data.remote.dto.BankConstantsResponse

interface ProfileRepository {
    suspend fun getBankConstants(): Result<BankConstantsResponse>
}