package com.sofia.mobile.config.preferences.language

import androidx.annotation.StringRes
import com.sofia.mobile.R
import java.util.Locale

enum class Language(
    val locale: Locale,
    @StringRes val resId: Int
){
    PT_BR(Locale("pt", "BR"), R.string.pt_br),
    EN_US(Locale("en", "US"), R.string.en_usa)
}