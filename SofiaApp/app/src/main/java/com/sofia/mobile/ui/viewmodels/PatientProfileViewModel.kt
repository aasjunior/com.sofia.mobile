package com.sofia.mobile.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofia.mobile.data.PacienteRepository
import com.sofia.mobile.models.PacienteModel
import kotlinx.coroutines.launch

class PatientProfileViewModel(private val repository: PacienteRepository) : ViewModel() {
    // O estado do paciente
    val patient = mutableStateOf<PacienteModel?>(null)
    val errorMessage = mutableStateOf<String?>(null)

    // Um método que busca os detalhes do paciente
    fun fetchPatient(id: Long) {
        viewModelScope.launch {
            try{
                patient.value = repository.getPatient(id)
            }catch(e: Exception){
                errorMessage.value = e.message
            }
        }
    }
}