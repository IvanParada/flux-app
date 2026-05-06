package com.nsqws.flux.features.payment.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nsqws.flux.features.payment.PaymentViewModel

@Composable
fun PaymentRoute(
    viewModel: PaymentViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    PaymentScreen(
        state = state,
        onDigitClick = viewModel::onDigitClick,
        onDeleteClick = viewModel::onDeleteClick,
    )

}