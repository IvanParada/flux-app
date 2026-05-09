package com.nsqws.flux.features.auth.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateBankDataRequest(
    @SerialName("bank_holder_id")
    val bankHolderId: String,

    @SerialName("bank_number")
    val bankNumber: String,

    @SerialName("bank_type")
    val bankType: String,

    @SerialName("bank_institution_id")
    val bankInstitutionId: String
)