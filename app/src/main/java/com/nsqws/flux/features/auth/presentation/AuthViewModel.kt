package com.nsqws.flux.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsqws.flux.features.auth.data.repository.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.timer

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: IAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    private var timerJob: Job? = null
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
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            repository.register(
                email = currentState.email,
                password = currentState.password,
                rut = currentState.rut
            ).onSuccess {
                startResendTimer()
                _state.update { it.copy(
                    isLoading = false,
                    navigateToVerify = true
                ) }
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

    fun login() {
        val currentState = _state.value
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            repository.login(currentState.email, currentState.password)
                .onSuccess {
                    _state.update { it.copy(isLoading = false, isSuccess = true) }
                }
                .onFailure { error ->
                    startResendTimer()
                    if (error.message == "PENDING_VERIFICATION") {
                        _state.update { it.copy(
                            isLoading = false,
                            navigateToVerify = true,
                            error = null
                        ) }
                    } else {
                        _state.update { it.copy(isLoading = false, error = error.message) }
                    }
                }
        }
    }

    fun resendCode() {
        val emailActual = _state.value.email

        if (emailActual.isBlank()) {
            _state.update { it.copy(error = "El correo no puede estar vacío") }
            return
        }

        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            repository.resendCode(emailActual).onSuccess {
                _state.update { it.copy(
                    isLoading = false,
                    error = "CÓDIGO_REENVIADO"
                ) }
            }.onFailure { error ->
                _state.update { it.copy(isLoading = false, error = error.message) }
            }
        }
    }

    fun startResendTimer() {
        timerJob?.cancel()
        _state.update { it.copy(resendCountdown = 60, isResendEnabled = false) }
        timerJob = viewModelScope.launch {
            while (_state.value.resendCountdown > 0) {
                delay(1000L)
                _state.update { it.copy(resendCountdown = it.resendCountdown - 1) }
            }
            _state.update { it.copy(isResendEnabled = true) }
        }
    }

    fun resetState() {
        _state.value = AuthState()
    }
}