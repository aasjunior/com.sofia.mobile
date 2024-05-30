package com.sofia.mobile.domain.checklist

import com.sofia.mobile.domain.common.enums.Checklist
import com.sofia.mobile.domain.common.enums.ChecklistType

data class TestResponse(
    val testId: String,
    val testName: Checklist,
    val testType: ChecklistType,
    val registerDateTime: String,
    val result: Boolean
)