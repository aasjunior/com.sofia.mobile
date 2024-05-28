package com.sofia.mobile.domain.common.enums

import androidx.annotation.StringRes
import com.sofia.mobile.R

enum class Gender(@StringRes val resId: Int) {
    Male(R.string.gender_male),
    Female(R.string.gender_female)
}