package com.sofia.mobile.domain.common.enums

import androidx.annotation.StringRes
import com.sofia.mobile.R

enum class ChecklistType(@StringRes val resId: Int) {
    INTERVENTION(R.string.checklist_type_intervention),
    SCREENING(R.string.checklist_type_screening)
}