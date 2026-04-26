package com.nsqws.flux.features.auth.domain


fun isValidPassword(password: String): Boolean {
    return password.length >= 8
}