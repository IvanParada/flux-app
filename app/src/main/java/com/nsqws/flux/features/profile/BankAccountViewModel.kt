package com.nsqws.flux.features.profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsqws.flux.features.auth.domain.validator.isRealisticRut
import com.nsqws.flux.features.profile.domain.repository.ProfileRepository
import com.nsqws.flux.features.profile.presentation.utils.isValidAccountNumberForFintocChile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankAccountViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BankAccountState())
    val state = _state.asStateFlow()

    init {
        loadBankConstants()
    }

    private fun loadBankConstants() {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            repository.getBankConstants()
                .onSuccess { data ->
                    _state.update {
                        it.copy(
                            banks = data.banks,
                            accountTypes = data.accountTypes,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Error al cargar información bancaria"
                        )
                    }
                }
        }
    }

    fun onBankSelected(bankId: String) {
        _state.update { it.copy(selectedBankId = bankId) }
    }

    fun onAccountTypeSelected(typeId: String) {
        _state.update { it.copy(selectedAccountTypeId = typeId) }
    }

    fun onAccountNumberChange(value: String) {
        _state.update { it.copy(accountNumber = value) }
    }

    fun onRutChange(value: String) {
        _state.update { it.copy(rut = value) }
    }

    fun saveBankAccount() {
        val currentState = _state.value

        if (currentState.rut.isBlank()) {
            _state.update { it.copy(error = "Ingrese el RUT") }
            return
        }

        if (!isRealisticRut(currentState.rut)) {
            _state.update { it.copy(error = "Ingrese un RUT válido") }
            return
        }

        if (currentState.selectedBankId.isBlank()) {
            _state.update { it.copy(error = "Seleccione un banco") }
            return
        }

        if (currentState.selectedAccountTypeId.isBlank()) {
            _state.update { it.copy(error = "Seleccione un tipo de cuenta") }
            return
        }

        if (currentState.accountNumber.isBlank()) {
            _state.update { it.copy(error = "Ingrese el número de cuenta") }
            return
        }

        if (!isValidAccountNumberForFintocChile(currentState.accountNumber)) {
            _state.update { it.copy(error = "Ingrese un número de cuenta válido") }
            return
        }

        _state.update { it.copy(isSaving = true, error = null) }

        viewModelScope.launch {
            repository.updateBankData(
                bankHolderId = currentState.rut,
                bankNumber = currentState.accountNumber,
                bankType = currentState.selectedAccountTypeId,
                bankInstitutionId = currentState.selectedBankId
            ).onSuccess {
                _state.update {
                    it.copy(
                        isSaving = false,
                        isSuccess = true,
                        error = null
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isSaving = false,
                        isSuccess = false,
                        error = error.message ?: "Error al guardar datos bancarios"
                    )
                }
            }
        }
    }

    fun resetStatus() {
        _state.update { it.copy(error = null, isSuccess = false) }
    }
}