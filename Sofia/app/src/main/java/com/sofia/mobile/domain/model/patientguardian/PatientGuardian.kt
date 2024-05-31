package com.sofia.mobile.domain.model.patientguardian

import com.sofia.mobile.domain.common.enums.Kinship

data class PatientGuardian(
    val patientId: String?,
    val guardianId: String?,
    val kinship: Kinship?
)

fun PatientGuardian.toState(): PatientGuardianState {
    val patientGuardianState = PatientGuardianState()
    patientGuardianState.updatePatientId(this.patientId ?: "")
    patientGuardianState.updateGuardianId(this.guardianId ?: "")
    patientGuardianState.updateKinship(this.kinship!!)
    return patientGuardianState
}

