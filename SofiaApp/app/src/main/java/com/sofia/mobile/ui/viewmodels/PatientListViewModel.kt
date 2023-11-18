package com.sofia.mobile.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofia.mobile.data.PacienteRepository
import com.sofia.mobile.models.PacienteModel
import kotlinx.coroutines.launch

class PatientListViewModel(private val repository: PacienteRepository) : ViewModel() {
    // O estado da lista de pacientes
    val patients = mutableStateOf<List<PacienteModel>>(emptyList())
    val errorMessage = mutableStateOf<String?>(null)
    val successMessage = mutableStateOf<String?>(null)

    val totalChecked: Int
        get() = patients.value.count { it.isSelected }

    // Um método que busca a lista de pacientes
    fun fetchPatients() {
        viewModelScope.launch {
            try{
                patients.value = repository.listPatients()
            }catch(e: Exception){
                errorMessage.value = e.message
            }
        }
    }

    fun selectPatient(patient: PacienteModel, isSelected: Boolean) {
        val index = patients.value.indexOf(patient)
        patients.value = patients.value.toMutableList().apply {
            this[index] = this[index].copy(isSelected = isSelected)
        }
    }

    fun selectAllPatients(isSelected: Boolean) {
        patients.value = patients.value.map { it.copy(isSelected = isSelected) }
    }

    fun deleteSelectedPatients() {
        viewModelScope.launch {
            try {
                patients.value.filter { it.isSelected }.forEach { repository.deletePatient(it.id) }
                fetchPatients()
                successMessage.value = "Pacientes selecionados deletados com sucesso!"
            } catch(e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun deselectAllPatients() {
        selectAllPatients(false)
    }
}