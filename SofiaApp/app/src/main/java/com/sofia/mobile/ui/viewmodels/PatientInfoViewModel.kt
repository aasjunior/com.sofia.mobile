package com.sofia.mobile.ui.viewmodels

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class PatientInfoViewModel : ViewModel() {
    val savedStateHandle = SavedStateHandle()

    var nomePaciente by mutableStateOf("")
    var sobrenomePaciente by mutableStateOf("")
    var selectedOptionText by mutableStateOf("")
    var sexoState = mutableIntStateOf(0)

    init {
        savedStateHandle.set("nomePaciente", nomePaciente)
        savedStateHandle.set("sobrenomePaciente", sobrenomePaciente)
        savedStateHandle.set("selectedOptionText", selectedOptionText)
        savedStateHandle.set("sexoState", sexoState)
    }

    fun onSave() {
        savedStateHandle.set("nomePaciente", nomePaciente)
        savedStateHandle.set("sobrenomePaciente", sobrenomePaciente)
        savedStateHandle.set("selectedOptionText", selectedOptionText)
        savedStateHandle.set("sexoState", sexoState)
    }

    fun onRestore() {
        nomePaciente = savedStateHandle.get("nomePaciente") ?: ""
        sobrenomePaciente = savedStateHandle.get("sobrenomePaciente") ?: ""
        selectedOptionText = savedStateHandle.get("selectedOptionText") ?: ""
        sexoState = savedStateHandle.get("sexoState") ?: mutableIntStateOf(0)
    }

    fun onNomePacienteChange(novoNomePaciente: String) {
        nomePaciente = novoNomePaciente
    }

    fun onSobrenomePacienteChange(novoSobrenomePaciente: String) {
        sobrenomePaciente = novoSobrenomePaciente
    }

    fun onSelectedOptionChange(novaSelectedOptionText: String) {
        selectedOptionText = novaSelectedOptionText
    }

    fun onSexoStateSelected(novoSexoState: MutableIntState) {
        sexoState = novoSexoState
    }
}
