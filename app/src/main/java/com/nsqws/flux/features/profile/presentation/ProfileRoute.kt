package com.nsqws.flux.features.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nsqws.flux.features.profile.ProfileViewModel

@Composable
fun ProfileRoute(
    onBankAccountClick: () -> Unit,
    onLogoutClick: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val state by profileViewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        state = state,
        onBankAccountClick = onBankAccountClick,
        onLogoutClick = {
            profileViewModel.logout()
            onLogoutClick()
        }
    )
}