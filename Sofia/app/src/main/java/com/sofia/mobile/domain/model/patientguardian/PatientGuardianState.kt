package com.sofia.mobile.domain.model.patientguardian

import com.sofia.mobile.domain.common.enums.Kinship
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class PatientGuardianState(
    private val _patientId: MutableStateFlow<String?> = MutableStateFlow(null),
    private val _guardianId: MutableStateFlow<String?> = MutableStateFlow(null),
    private val _kinship: MutableStateFlow<Kinship?> = MutableStateFlow(null)
) {
    val patientId: StateFlow<String?> by ::_patientId
    val guardianId: StateFlow<String?> by ::_guardianId
    val kinship: StateFlow<Kinship?> by ::_kinship

    fun updatePatientId(newPatientId: String){
        this._patientId.value = newPatientId
    }

    fun updateGuardianId(newGuardianId: String){
        this._guardianId.value = newGuardianId
    }

    fun updateKinship(newKinship: Kinship){
        this._kinship.value = newKinship
    }
}