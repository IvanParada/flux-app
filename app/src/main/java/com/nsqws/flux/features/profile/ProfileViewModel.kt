package com.nsqws.flux.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsqws.flux.core.data.local.TokenManager
import com.nsqws.flux.features.profile.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            tokenManager.clearAll()
        }
    }

}