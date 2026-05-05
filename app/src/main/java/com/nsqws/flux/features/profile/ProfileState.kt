package com.nsqws.flux.features.profile

data class ProfileState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)