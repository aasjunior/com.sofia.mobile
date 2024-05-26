package com.sofia.mobile.ui.viewmodel.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digitsOnly = text.text.filter { it.isDigit() }
        val transformedText = buildString {
            digitsOnly.forEachIndexed { index, char ->
                when (index) {
                    0 -> append("($char")
                    1 -> append("$char) ")
                    6 -> append("$char-")
                    else -> append(char)
                }
            }
        }

        val identityTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val transformedOffset = when {
                    offset <= 2 -> offset
                    offset <= 6 -> offset + 1
                    else -> offset + 2
                }
                return min(transformedOffset, transformedText.length)
            }
            override fun transformedToOriginal(offset: Int): Int {
                val originalOffset = when {
                    offset <= 1 -> offset
                    offset <= 4 -> offset - 1
                    offset <= 9 -> offset - 3
                    else -> offset - 4
                }
                return min(originalOffset, text.text.length)
            }
        }

        return TransformedText(AnnotatedString(transformedText), identityTranslator)
    }
}
