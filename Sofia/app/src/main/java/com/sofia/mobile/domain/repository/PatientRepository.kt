package com.sofia.mobile.domain.repository

import com.sofia.mobile.domain.model.patient.Patient
import com.sofia.mobile.domain.model.patientguardian.PatientGuardianRequest
import com.sofia.mobile.domain.service.PatientService

class PatientRepository(private val patientService: PatientService) {
    suspend fun listPatients(): List<Patient>{
        return patientService.listPatients()
    }

    suspend fun getPatient(id: String): Patient?{
        return patientService.getPatient(id)
    }

    suspend fun savePatientWithGuardian(request: PatientGuardianRequest): Patient{
        return patientService.savePatientWithGuardian(request)
    }

    suspend fun deletePatient(id: String){
        patientService.deletePatient(id)
    }
}