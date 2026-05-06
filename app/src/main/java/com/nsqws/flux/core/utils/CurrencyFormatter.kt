package com.nsqws.flux.core.utils

import java.text.NumberFormat
import java.util.Locale

fun Long.toClpAmount(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-CL"))
    return formatter.format(this)
}