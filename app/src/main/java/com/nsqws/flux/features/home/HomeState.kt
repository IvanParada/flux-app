package com.nsqws.flux.features.home

data class HomeState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)