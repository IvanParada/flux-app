package com.nsqws.flux.features.profile.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BankConstantsResponse(
    @SerialName("banks") val banks: List<BankItemDTO>,
    @SerialName("accountTypes") val accountTypes: List<AccountTypeDTO>
)

@Serializable
data class BankItemDTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String
)

@Serializable
data class AccountTypeDTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String
)
