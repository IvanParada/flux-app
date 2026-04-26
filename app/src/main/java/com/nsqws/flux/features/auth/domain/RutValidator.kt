package com.nsqws.flux.features.auth.domain

import kotlin.text.iterator

fun normalizeRut(rut: String): String {
    return rut
        .replace(".", "")
        .replace("-", "")
        .uppercase()
}

fun isValidRut(rut: String): Boolean {
    val cleanRut = normalizeRut(rut)

    if (cleanRut.length !in 8..9) return false

    val body = cleanRut.dropLast(1)
    val dv = cleanRut.last()

    if (!body.all { it.isDigit() }) return false
    if (!(dv.isDigit() || dv == 'K')) return false

    var sum = 0
    var multiplier = 2

    for (digit in body.reversed()) {
        sum += digit.digitToInt() * multiplier
        multiplier = if (multiplier == 7) 2 else multiplier + 1
    }

    val expected = 11 - (sum % 11)

    val expectedDv = when (expected) {
        11 -> '0'
        10 -> 'K'
        else -> expected.digitToChar()
    }

    return dv == expectedDv
}

fun isRealisticRut(rut: String): Boolean {
    val cleanRut = normalizeRut(rut)

    if (!isValidRut(cleanRut)) return false

    val body = cleanRut.dropLast(1)

    if (body.all { it == body.first() }) return false

    val number = body.toIntOrNull() ?: return false

    if (number < 1_000_000) return false

    if (number > 50_000_000) return false

    return true
}