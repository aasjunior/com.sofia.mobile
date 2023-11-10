package com.sofia.mobile.ui.viewmodels

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PatientInfoViewModel : ViewModel() {
    private val _nome = MutableStateFlow<String>("")
    private val _sobrenome = MutableStateFlow<String>("")
    private val _sexo = MutableStateFlow<String>("")
    private val _etnia = MutableStateFlow<String>("")

    val nome = _nome.asStateFlow()
    val sobrenome = _sobrenome.asStateFlow()
    val sexo = _sexo.asStateFlow()
    val etnia = _etnia.asStateFlow()

    fun updateNome(novoNome: String) {
        _nome.value = novoNome
    }

    fun updateSobrenome(novoSobrenome: String) {
        _sobrenome.value = novoSobrenome
    }
}
