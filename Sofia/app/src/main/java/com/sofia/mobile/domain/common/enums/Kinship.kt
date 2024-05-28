package com.sofia.mobile.domain.common.enums

import androidx.annotation.StringRes
import com.sofia.mobile.R

enum class Kinship(@StringRes val resId: Int) {
    Mother(R.string.kinship_mother),
    Father(R.string.kinship_father),
    Sister(R.string.kinship_sister),
    Brother(R.string.kinship_brother),
    Aunt(R.string.kinship_aunt),
    Uncle(R.string.kinship_uncle),
    Grandmother(R.string.kinship_grandmother),
    Grandfather(R.string.kinship_grandfather)
}