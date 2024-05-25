package com.sofia.mobile.domain.model.guardian

import com.sofia.mobile.domain.model.patientguardian.PatientGuardian

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
