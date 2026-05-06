package com.nsqws.flux.features.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nsqws.flux.features.profile.ProfileViewModel

@Composable
fun ProfileRoute(
    onLogoutClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        state = state,
        onLogoutClick = {
            viewModel.logout()
            onLogoutClick()
        }
    )

}