package com.sofia.mobile.domain.service

import com.sofia.mobile.domain.model.patient.Patient
import com.sofia.mobile.domain.model.patient.PatientRequest
import com.sofia.mobile.domain.model.patientguardian.PatientGuardianRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PatientService {
    @GET("/patients")
    suspend fun listPatients(): List<Patient>

    @GET("/patients/{id}")
    suspend fun getPatient(@Path("id") id: String): Patient?

    @POST("/patients/patient-with-guardian")
    suspend fun savePatientWithGuardian(@Body request: PatientGuardianRequest): Patient

    @PUT("/patients/patient-with-guardian/{id}")
    suspend fun updatePatientWithGuardian(@Path("id") id: String, @Body request: PatientGuardianRequest): Response<Patient>

    @DELETE("/patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): Response<Unit>
}