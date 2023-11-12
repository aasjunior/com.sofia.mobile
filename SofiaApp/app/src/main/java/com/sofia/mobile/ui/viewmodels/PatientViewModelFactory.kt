package com.sofia.mobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sofia.mobile.data.PacienteRepository

class PatientViewModelFactory(private val repository: PacienteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientInfoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
