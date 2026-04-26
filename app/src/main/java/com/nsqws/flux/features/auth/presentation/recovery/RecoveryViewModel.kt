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
    private val repository: AuthRepository // Tu repo debe tener los métodos de recovery
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) = _state.update { it.copy(email = email) }
    fun onCodeChange(code: String) {
        if (code.length <= 6) {
            _state.update { it.copy(code = code) }
        }
    }
    fun onPasswordChange(pass: String) = _state.update { it.copy(password = pass) }

    fun handleNext() {
        when (_state.value.recoveryStep) {
            0 -> sendCode()
            1 -> verifyCode()
            2 -> resetPassword()
        }
    }

    private fun sendCode() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(1000)
            _state.update { it.copy(isLoading = false, recoveryStep = 1) }
        }
    }

    private fun verifyCode() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(1000)
            _state.update { it.copy(isLoading = false, recoveryStep = 2) }
        }
    }

    private fun resetPassword() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(1000)
            _state.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }
}