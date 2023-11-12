package com.sofia.mobile.api

import com.sofia.mobile.domain.Paciente
import com.sofia.mobile.models.PacienteModel
import retrofit2.http.*


interface SofiaApiService {
    @GET("/pacientes")
    suspend fun listPatients(): List<PacienteModel>

    @GET("/pacientes/{id}")
    suspend fun getPatient(@Path("id") id: Long): PacienteModel?

    @POST("/pacientes")
    suspend fun savePatient(@Body patient: Paciente): Paciente

    @PUT("/pacientes/{id}")
    suspend fun updatePatient(@Path("id") id: Long, @Body patient: PacienteModel): PacienteModel

    @DELETE("/pacientes/{id}")
    suspend fun deletePatient(@Path("id") id: Long)
}