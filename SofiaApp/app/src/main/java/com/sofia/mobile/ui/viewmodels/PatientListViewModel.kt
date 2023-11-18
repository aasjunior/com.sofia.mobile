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
}