package com.sofia.mobile.domain.model.guardian

import com.sofia.mobile.domain.model.patientguardian.PatientGuardian
import com.sofia.mobile.domain.model.patientguardian.toState

data class Guardian(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,

    // Usar o patientId como chave
    val patients: HashMap<String, PatientGuardian>
){
    fun addPatient(patientGuardian: PatientGuardian){
        this.patients[patientGuardian.patientId!!] = patientGuardian
    }
}

fun Guardian.toState(): GuardianState {
    val guardianState = GuardianState()
    guardianState.updateId(this.id ?: "")
    guardianState.updateFirstName(this.firstName)
    guardianState.updateLastName(this.lastName)
    guardianState.updatePhone(this.phone)
    guardianState.updateEmail(this.email)
    guardianState.updatePatientsList(this.patients.values.map { it.toState() })
    return guardianState
}
