package com.nsqws.flux.features.profile

import com.nsqws.flux.features.profile.data.remote.dto.response.AccountTypeDTO
import com.nsqws.flux.features.profile.data.remote.dto.response.BankItemDTO

data class BankAccountState (
    val banks: List<BankItemDTO> = emptyList(),
    val accountTypes: List<AccountTypeDTO> = emptyList(),

    val selectedBankId: String = "",
    val selectedAccountTypeId: String = "",
    val accountNumber: String = "",
    val rut: String = "",

    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)