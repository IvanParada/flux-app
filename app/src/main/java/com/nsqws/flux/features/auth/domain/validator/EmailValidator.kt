package com.nsqws.flux.features.auth.domain.validator


import android.util.Patterns

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS
        .matcher(email.trim())
        .matches()
}