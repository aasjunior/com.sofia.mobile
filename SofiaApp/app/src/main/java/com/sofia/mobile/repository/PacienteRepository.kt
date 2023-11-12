package com.sofia.mobile.data

import com.sofia.mobile.api.SofiaApiService
import com.sofia.mobile.domain.Paciente
import com.sofia.mobile.models.PacienteModel

class PacienteRepository(private val api: SofiaApiService) {

    suspend fun listPatients(): List<PacienteModel> {
        return api.listPatients()
    }

    suspend fun getPatient(id: Long): PacienteModel? {
        return api.getPatient(id)
    }

    suspend fun savePatient(patient: Paciente): Paciente {
        return api.savePatient(patient)
    }

    suspend fun updatePatient(id: Long, patient: PacienteModel): PacienteModel {
        return api.updatePatient(id, patient)
    }

    suspend fun deletePatient(id: Long) {
        api.deletePatient(id)
    }
}
