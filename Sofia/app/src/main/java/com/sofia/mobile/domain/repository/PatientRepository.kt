package com.sofia.mobile.domain.repository

import android.util.Log
import com.sofia.mobile.domain.model.patient.Patient
import com.sofia.mobile.domain.model.patientguardian.PatientGuardianRequest
import com.sofia.mobile.domain.service.PatientService
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

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

    suspend fun updatePatientWithGuardian(id: String, request: PatientGuardianRequest): Response<Patient> {
        try{
            return patientService.updatePatientWithGuardian(id, request)
        }catch(e: Exception){
            return Response.error(400, e.message?.toResponseBody()!!)
            Log.i("UpdatePatientError", "${e.message}")
        }
    }

    suspend fun deletePatient(id: String){
        patientService.deletePatient(id)
    }
}