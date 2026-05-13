package com.nsqws.flux.features.profile.presentation.utils

private const val MAX_ACCOUNT_NUMBER_LENGTH = 20

fun sanitizeAccountNumberInput(input: String): String {
    return input
        .filter { it.isDigit() }
        .take(MAX_ACCOUNT_NUMBER_LENGTH)
}

fun isValidAccountNumberForFintocChile(accountNumber: String): Boolean {
    return accountNumber.matches(Regex("^(?!0+$)\\d{5,20}$"))
}