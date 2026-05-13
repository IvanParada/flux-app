package com.nsqws.flux.features.profile.presentation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.Composable
import com.nsqws.flux.features.profile.BankAccountViewModel

@Composable
fun BankAccountRoute(
    onBackClick: () -> Unit,
    viewModel: BankAccountViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BankAccountScreen(
        state = state,
        onBackClick = onBackClick,
        onBankSelected = viewModel::onBankSelected,
        onAccountTypeSelected = viewModel::onAccountTypeSelected,
        onAccountNumberChange = viewModel::onAccountNumberChange,
        onRutChange = viewModel::onRutChange,
        onSaveClick = viewModel::saveBankAccount
    )
}