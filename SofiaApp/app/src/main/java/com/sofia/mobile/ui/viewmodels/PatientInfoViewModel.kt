package com.sofia.mobile.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PatientInfoViewModel : ViewModel() {
    var nomePaciente by mutableStateOf("")
    var sobrenomePaciente by mutableStateOf("")
    var selectedOptionText by mutableStateOf("")
    var sexoState = mutableIntStateOf(0)
}
