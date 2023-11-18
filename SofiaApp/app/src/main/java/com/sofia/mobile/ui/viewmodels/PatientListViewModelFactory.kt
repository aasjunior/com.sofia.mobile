package com.sofia.mobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sofia.mobile.data.PacienteRepository

class PatientListViewModelFactory(private val repository: PacienteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PatientListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}