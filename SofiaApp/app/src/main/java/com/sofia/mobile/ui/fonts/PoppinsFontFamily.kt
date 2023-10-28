package com.sofia.mobile.ui.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.sofia.mobile.R

class PoppinsFontFamily{
    private val fontStyles: List<Font> = listOf(
        Font(R.font.poppins_regular),
        Font(R.font.poppins_medium),
        Font(R.font.poppins_semibold)
    )

    val regular: FontFamily = FontFamily(fontStyles[0])
    val medium: FontFamily = FontFamily(fontStyles[1])
    val semibold: FontFamily = FontFamily(fontStyles[2])
}