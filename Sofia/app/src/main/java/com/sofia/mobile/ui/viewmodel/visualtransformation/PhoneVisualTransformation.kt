package com.sofia.mobile.ui.viewmodel.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = buildString {
            text.text.forEachIndexed { index, char ->
                when (index) {
                    0 -> append("($char")
                    1 -> append("$char) ")
                    6 -> append("$char-")
                    else -> append(char)
                }
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 0 -> offset
                    offset <= 1 -> offset + 1
                    offset <= 6 -> offset + 3
                    else -> offset + 4
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 1 -> offset
                    offset <= 4 -> offset - 1
                    offset <= 9 -> offset - 3
                    else -> offset - 4
                }
            }
        }

        return TransformedText(AnnotatedString(transformedText), offsetTranslator)
    }
}

