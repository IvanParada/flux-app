package com.nsqws.flux.features.auth.presentation.utils

fun sanitizeRutInput(
    input: String,
    previous: String
): String {
    val clean = input
        .filter { it.isDigit() || it.equals('k', ignoreCase = true) }
        .uppercase()

    if (previous.endsWith("K") && clean.length > previous.length) {
        return previous
    }

    if (previous.length >= 9 && clean.length > previous.length) {
        return previous
    }

    val kIndex = clean.indexOf("K")

    return if (kIndex >= 0) {
        val beforeK = clean
            .take(kIndex)
            .filter { it.isDigit() }
            .take(8)

        if (beforeK.length in 7..8) {
            beforeK + "K"
        } else {
            beforeK
        }
    } else {
        clean
            .filter { it.isDigit() }
            .take(9)
    }
}