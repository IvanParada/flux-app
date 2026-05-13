package com.nsqws.flux.features.profile.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UpdateBankDataDto(
    @SerialName("bank_holder_id") val bankHolderId: String,
    @SerialName("bank_number") val bankNumber: String,
    @SerialName("bank_type") val bankType: String,
    @SerialName("bank_institution_id") val bankInstitutionId: String
)