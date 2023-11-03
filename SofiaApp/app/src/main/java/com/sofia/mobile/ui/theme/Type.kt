package com.sofia.mobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sofia.mobile.ui.theme.fonts.PoppinsFontFamily

private val poppins = PoppinsFontFamily()

object SofiaTypography{
    val title20 = TextStyle(
        fontFamily = poppins.medium,
        fontSize = 20.sp
    )

    val title24 = TextStyle(
        fontFamily = poppins.semibold,
        fontSize = 24.sp
    )

    val title28 = TextStyle(
        fontFamily = poppins.semibold,
        fontSize = 28.sp
    )

    val text1 = TextStyle(
        fontFamily = poppins.regular,
        fontSize = 14.sp
    )

    val text2 = TextStyle(
        fontFamily = poppins.semibold,
        fontSize = 14.sp
    )

    val text12 = TextStyle(
        fontFamily = poppins.regular,
        fontSize = 12.sp
    )

    val text16 = TextStyle(
        fontFamily = poppins.medium,
        fontSize = 16.sp
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)