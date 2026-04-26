package com.nsqws.flux.features.auth.presentation.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class RutVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text
            .filter { it.isDigit() || it.equals('k', ignoreCase = true) }
            .uppercase()
            .take(9)

        val formatted = formatRut(raw)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return formatRut(raw.take(offset)).length
            }

            override fun transformedToOriginal(offset: Int): Int {
                return formatted
                    .take(offset.coerceIn(0, formatted.length))
                    .count { it.isDigit() || it.equals('k', ignoreCase = true) }
                    .coerceIn(0, raw.length)
            }
        }

        return TransformedText(
            AnnotatedString(formatted),
            offsetMapping
        )
    }

    private fun formatRut(raw: String): String {
        if (raw.isEmpty()) return ""
        if (raw.length == 1) return raw

        val body = raw.dropLast(1)
        val dv = raw.last()

        val bodyFormatted = body
            .reversed()
            .chunked(3)
            .joinToString(".")
            .reversed()

        return "$bodyFormatted-$dv"
    }
}