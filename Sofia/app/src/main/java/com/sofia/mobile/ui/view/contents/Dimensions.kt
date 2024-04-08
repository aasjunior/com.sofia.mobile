package com.sofia.mobile.ui.view.contents

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class RelativeDimensions(val screenWidth: Dp){
    val contentWidth: Dp = screenWidth * DimensionsPercent.ContentWidth

    val formField: Dp = screenWidth * DimensionsPercent.FormField
    val formFieldIntro: Dp = screenWidth * DimensionsPercent.FormFieldIntro

    val cardPadding1: Dp = screenWidth * DimensionsPercent.CardPadding1
    val cardPadding2: Dp = screenWidth * DimensionsPercent.CardPadding2
    val cardPadding3: Dp = screenWidth * DimensionsPercent.CardPadding3
}

object Dimensions{
    val formFild264: Dp = 264.dp
    val formFild315: Dp = 315.dp

    val padding15: Dp = 15.dp
    val padding20: Dp = 20.dp
    val padding24: Dp = 24.dp

    val roundedCorner12 = 12.dp
}


private object DimensionsPercent{
    const val ContentWidth = 0.875f // 315 de 360

    const val FormField = 0.733f // 240 de 360
    const val FormFieldIntro = 0.839f // 302 de 360

    const val CardPadding1 = 0.042f // 15 de 360
    const val CardPadding2 = 0.055f // 20 de 360
    const val CardPadding3 = 0.067f // 24 de 360
}