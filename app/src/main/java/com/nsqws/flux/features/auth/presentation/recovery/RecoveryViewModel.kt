package com.nsqws.flux.features.auth.presentation.recovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsqws.flux.features.auth.data.repository.AuthRepository
import com.nsqws.flux.features.auth.presentation.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecoveryViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) =
        _state.update { it.copy(email = email) }

    fun onCodeChange(code: String) {
        if (code.length <= 6) {
            _state.update { it.copy(code = code) }
        }
    }

    fun onNewPasswordChange(pass: String) =
        _state.update { it.copy(newPassword = pass) }

    fun handleNext() {
        when (_state.value.recoveryStep) {
            0 -> sendCode()
            1 -> verifyCode()
            2 -> resetPassword()
        }
    }

    private fun sendCode() {
        val currentState = _state.value

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            repository.forgotPassword(currentState.email)
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            recoveryStep = 1
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
        }
    }

    private fun verifyCode() {
        val currentState = _state.value

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            repository.verifyResetCode(
                email = currentState.email,
                code = currentState.code
            ).onSuccess {
                _state.update {
                    it.copy(
                        isLoading = false,
                        recoveryStep = 2
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
        }
    }

    private fun resetPassword() {
        val currentState = _state.value

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            repository.resetPassword(
                email = currentState.email,
                code = currentState.code,
                newPassword = currentState.newPassword
            ).onSuccess {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
        }
    }
}