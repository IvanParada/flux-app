package com.nsqws.flux.features.auth.domain.validator


fun isValidPassword(password: String): Boolean {
    return password.length >= 8
}