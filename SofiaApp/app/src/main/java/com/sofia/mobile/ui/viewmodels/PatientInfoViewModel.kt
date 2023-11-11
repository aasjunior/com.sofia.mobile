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
    private val _sexo = MutableStateFlow<Int?>(null)
    private val _casosFamilia = MutableStateFlow<Int?>(null)
    private val _complicacoesGravidez = MutableStateFlow<Int?>(null)
    private val _prematuro = MutableStateFlow<Int?>(null)
    private val _etnia = MutableStateFlow<String>("")

    private val _nomeResponsavel = MutableStateFlow<String>("")
    private val _sobrenomeResponsavel = MutableStateFlow<String>("")
    private val _parentesco = MutableStateFlow<String>("")
    private val _email = MutableStateFlow<String>("")
    private val _celular = MutableStateFlow<String>("")

    val nome = _nome.asStateFlow()
    val sobrenome = _sobrenome.asStateFlow()
    val sexo = _sexo.asStateFlow()
    val casosFamilia = _casosFamilia.asStateFlow()
    val complicacoesGravidez = _complicacoesGravidez.asStateFlow()
    val prematuro = _prematuro.asStateFlow()
    val etnia = _etnia.asStateFlow()

    val nomeResponsavel = _nomeResponsavel.asStateFlow()
    val sobrenomeResponsavel = _sobrenomeResponsavel.asStateFlow()
    val parentesco = _parentesco.asStateFlow()
    val email = _email.asStateFlow()
    val celular = _celular.asStateFlow()

    fun updateNome(novoNome: String) {
        _nome.value = novoNome
    }

    fun updateSobrenome(novoSobrenome: String) {
        _sobrenome.value = novoSobrenome
    }

    fun updateSexo(novoSexo: Int) {
        _sexo.value = novoSexo
    }

    fun updateCasosFamilia(novoCasosFamilia: Int) {
        _casosFamilia.value = novoCasosFamilia
    }

    fun updateComplicacoesGravidez(novoComplicacoesGravidez: Int) {
        _complicacoesGravidez.value = novoComplicacoesGravidez
    }

    fun updatePrematuro(novoPrematuro: Int) {
        _prematuro.value = novoPrematuro
    }

    fun updateNomeResponsavel(novoNomeResponsavel: String) {
        _nomeResponsavel.value = novoNomeResponsavel
    }

    fun updateSobrenomeResponsavel(novoSobrenomeResponsavel: String) {
        _sobrenomeResponsavel.value = novoSobrenomeResponsavel
    }

    fun updateParentesco(novoParentesco: String) {
        _parentesco.value = novoParentesco
    }

    fun updateEmail(novoEmail: String) {
        _email.value = novoEmail
    }

    fun updateCelular(novoCelular: String) {
        _celular.value = novoCelular
    }

    /*
    suspend fun SendData(){
        SQLite()
        SendRemote()
    }

     */
}
