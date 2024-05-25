package com.sofia.mobile.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.domain.model.patient.Patient
import kotlinx.coroutines.launch

class PatientListViewModel : ViewModel() {
    private val patientRepository = ApiClient.patientRepository

    private val _patients = mutableStateListOf<Patient>()
    val patients: List<Patient>
        get() = _patients

    val errorMessage = mutableStateOf<String?>(null)
    val successMessage = mutableStateOf<String?>(null)

    val totalChecked: Int
        get() = _patients.count { it.isSelected }

    fun fetchPatients() {
        viewModelScope.launch {
            try{
                _patients.clear()
                _patients.addAll(patientRepository.listPatients())
            }catch(e: Exception){
                errorMessage.value = e.message
            }
        }
    }

    fun selectPatient(patient: Patient, isSelected: Boolean) {
        val index = _patients.indexOfFirst { it.id == patient.id }
        if (index != -1) {
            _patients[index] = _patients[index].copy(isSelected = isSelected)
        }
    }
    fun selectAllPatients(isSelected: Boolean) {
        _patients.replaceAll { it.copy(isSelected = isSelected) }
    }

    fun deleteSelectedPatients(succesMessage: String) {
        viewModelScope.launch {
            try {
                val selectedPatients = _patients.filter { it.isSelected }
                _patients.filter { it.isSelected }.forEach { patientRepository.deletePatient(it.id!!) }
                _patients.removeAll(selectedPatients)
                successMessage.value = succesMessage
            } catch(e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun deselectAllPatients() {
        selectAllPatients(false)
    }
}