package com.sofia.mobile.domain.model.patient

import com.sofia.mobile.domain.common.enums.Ethnicity
import com.sofia.mobile.domain.common.enums.Gender
import com.sofia.mobile.domain.model.guardian.Guardian
import com.sofia.mobile.domain.model.guardian.GuardianRequest

data class PatientRequest(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val gender: Gender,
    val ethnicity: Ethnicity,
    val familyCases: Boolean,
    val pregnancyComplications: Boolean,
    val premature: Boolean
)