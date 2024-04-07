package com.sofia.mobile.domain.model.guardian

import com.sofia.mobile.domain.model.patientguardian.PatientGuardianDTO

data class GuardianDTO(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,

    // Usar o patientId como chave
    val patients: HashMap<String, PatientGuardianDTO>
){
    fun addPatient(patientGuardian: PatientGuardianDTO){
        this.patients[patientGuardian.patientId!!] = patientGuardian
    }
}
