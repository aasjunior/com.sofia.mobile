package com.sofia.mobile.domain.model.guardian

import com.sofia.mobile.domain.model.patientguardian.PatientGuardianState
import com.sofia.mobile.domain.model.person.PersonState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class GuardianState(
    private val _phone: MutableStateFlow<String> = MutableStateFlow(""),
    private val _email: MutableStateFlow<String> = MutableStateFlow(""),
    private val _patients: MutableStateFlow<List<PatientGuardianState>?> = MutableStateFlow(null)
): PersonState(){
    val phone: StateFlow<String> by ::_phone
    val email: StateFlow<String> by ::_email
    val patients: StateFlow<List<PatientGuardianState>?> by ::_patients

    fun updatePhone(newPhone: String){
        this._phone.value = newPhone
    }

    fun updateEmail(newEmail: String){
        this._email.value = newEmail
    }

    fun updatePatientsList(newPatientsList: List<PatientGuardianState>){
        this._patients.value = newPatientsList
    }

    fun newPatient(newPatient: PatientGuardianState){
        val currentPatients = _patients.value ?: emptyList()
        this._patients.value = currentPatients + newPatient
    }
}
