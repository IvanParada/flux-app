package com.nsqws.flux.features.history.presentation

import HistoryScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nsqws.flux.features.history.HistoryViewModel

@Composable
fun HistoryRoute(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    HistoryScreen(state = state)

}