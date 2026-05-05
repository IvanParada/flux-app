package com.nsqws.flux.features.history

data class HistoryState (

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)
