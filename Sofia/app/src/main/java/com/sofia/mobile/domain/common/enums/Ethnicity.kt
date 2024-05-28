package com.sofia.mobile.domain.common.enums

import androidx.annotation.StringRes
import com.sofia.mobile.R

enum class Ethnicity(@StringRes val resId: Int) {
    White(R.string.ethnicity_white),
    Black(R.string.ethnicity_black),
    Brown(R.string.ethnicity_brown),
    Indigenous(R.string.ethnicity_indigenous),
    Yellow(R.string.ethnicity_yellow),
    Other(R.string.ethnicity_other)
}