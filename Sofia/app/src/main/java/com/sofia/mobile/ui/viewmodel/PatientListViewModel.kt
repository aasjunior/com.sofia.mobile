package com.sofia.mobile.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofia.mobile.R
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.domain.model.patient.Patient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PatientListViewModel : ViewModel() {
    private val patientRepository = ApiClient.patientRepository

    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients: StateFlow<List<Patient>>
        get() = _patients

    val errorMessage = mutableStateOf<String?>(null)
    val successMessage = mutableStateOf<String?>(null)

    val totalChecked: Int
        get() = _patients.value.count { it.isSelected }

    init {
        fetchPatients()
    }

    fun fetchPatients() {
        viewModelScope.launch {
            try{
                _patients.value = patientRepository.listPatients()
            }catch(e: Exception){
                errorMessage.value = e.message
            }
        }
    }

    fun selectPatient(patient: Patient, isSelected: Boolean) {
        val index = _patients.value.indexOf(patient)
        _patients.value = _patients.value.toMutableList().apply {
            this[index] = this[index].copy(isSelected = isSelected)
        }
    }

    fun selectAllPatients(isSelected: Boolean) {
        _patients.value = _patients.value.map { it.copy(isSelected = isSelected) }
    }

    fun deleteSelectedPatients(succesMessage: String) {
        viewModelScope.launch {
            try {
                _patients.value.filter { it.isSelected }.forEach { patientRepository.deletePatient(it.id!!) }
                fetchPatients()
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