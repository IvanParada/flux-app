package com.nsqws.flux.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsqws.flux.features.auth.data.repository.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: IAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onRutChange(newRut: String) { _state.update { it.copy(rut = newRut) } }
    fun onEmailChange(newEmail: String) { _state.update { it.copy(email = newEmail) } }
    fun onPasswordChange(newPassword: String) { _state.update { it.copy(password = newPassword) } }

    fun onCodeChange(newCode: String) {
        if (newCode.length <= 6) {
            _state.update { it.copy(code = newCode) }
        }
    }

    fun register() {
        val currentState = _state.value
        if (currentState.rut.isBlank() || currentState.email.isBlank()) {
            _state.update { it.copy(error = "Campos obligatorios vacíos") }
            return
        }

        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            repository.register(
                email = currentState.email,
                password = currentState.password,
                rut = currentState.rut
            ).onSuccess {
                _state.update { it.copy(isLoading = false, isSuccess = true) }
            }.onFailure { error ->
                _state.update { it.copy(isLoading = false, error = error.message) }
            }
        }
    }

    fun verify() {
        val currentState = _state.value
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            repository.verify(
                email = currentState.email,
                code = currentState.code
            ).onSuccess {
                _state.update { it.copy(isLoading = false, isSuccess = true) }
            }.onFailure { error ->
                _state.update { it.copy(isLoading = false, error = error.message) }
            }
        }
    }

    fun login(){}
}