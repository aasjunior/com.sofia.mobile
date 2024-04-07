package com.sofia.mobile.domain.model.patientguardian

import com.sofia.mobile.domain.common.enums.Kinship

data class PatientGuardianDTO(
    val patientId: String?,
    val guardianId: String?,
    val kinship: Kinship?
)
