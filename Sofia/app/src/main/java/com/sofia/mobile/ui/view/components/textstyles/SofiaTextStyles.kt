package com.sofia.mobile.ui.view.components.textstyles

import com.sofia.mobile.ui.theme.SofiaColorScheme.Black
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.Gray1
import com.sofia.mobile.ui.theme.SofiaColorScheme.SoftPurple
import com.sofia.mobile.ui.theme.SofiaTypography.link
import com.sofia.mobile.ui.theme.SofiaTypography.text12
import com.sofia.mobile.ui.theme.SofiaTypography.text16
import com.sofia.mobile.ui.theme.SofiaTypography.textRegular
import com.sofia.mobile.ui.theme.SofiaTypography.textSemibold
import com.sofia.mobile.ui.theme.SofiaTypography.title20
import com.sofia.mobile.ui.theme.SofiaTypography.title24
import com.sofia.mobile.ui.theme.SofiaTypography.title28

object  SofiaTextStyles {
    val h1 = title28.copy(BrillantPurple)
    val h2 = title24.copy(BrillantPurple)
    val h3 = title20.copy(BrillantPurple)
    val text1 = textRegular.copy(Gray1)
    val text2 = textSemibold.copy(Gray1)
    val text3 = text16.copy(Gray1)
    val p = textRegular.copy(Black)
    val link1 = link.copy(Gray1)
    val link2 = link.copy(SoftPurple)
    val copyright = text2.copy(color = BrillantPurple)

    val label = text16.copy(BrillantPurple)
    val legend1 = text12.copy(Gray1)
    val legend2 = title20.copy(Black)
    val placeholder = textRegular.copy(BrillantPurple)
}