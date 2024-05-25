package com.sofia.mobile.domain.model.patientguardian

import com.sofia.mobile.domain.common.enums.Kinship
import com.sofia.mobile.domain.model.guardian.GuardianRequest
import com.sofia.mobile.domain.model.patient.PatientRequest

data class PatientGuardianRequest(
    val patientRequest: PatientRequest,
    val guardianRequest: GuardianRequest,
    val kinship: Kinship
)