package com.sofia.mobile.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sofia.mobile.data.PacienteRepository
import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.domain.Paciente
import com.sofia.mobile.domain.Parentesco
import com.sofia.mobile.domain.Responsavel
import com.sofia.mobile.domain.Sexo
import com.sofia.mobile.models.PacienteModel
import com.sofia.mobile.models.ResponsavelModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PatientViewModel(private val repository: PacienteRepository) : ViewModel() {
    //Paciente
    private val _pacienteId = MutableStateFlow<Long>(0)
    private val _responsavelId = MutableStateFlow<Long>(0)
    private val _nome = MutableStateFlow("")
    private val _sobrenome = MutableStateFlow("")
    private val _sexo = MutableStateFlow<Sexo?>(null)
    private val _dataNascimento = MutableStateFlow<LocalDate?>(null)
    private val _etnia = MutableStateFlow<Etnia?>(null)
    private val _casosFamilia = MutableStateFlow<Int?>(null)
    private val _complicacoesGravidez = MutableStateFlow<Int?>(null)
    private val _prematuro = MutableStateFlow<Int?>(null)
    private val _dataCadastro = MutableStateFlow<LocalDateTime?>(null)
    val _selectedOptionText = MutableStateFlow<String>("")
    val selectedOptionText: StateFlow<String> = _selectedOptionText.asStateFlow()

    val nome = _nome.asStateFlow()
    val sobrenome = _sobrenome.asStateFlow()
    val sexo: StateFlow<Sexo?> = _sexo.asStateFlow()
    val dataNascimento: StateFlow<LocalDate?> = _dataNascimento.asStateFlow()
    val etnia: StateFlow<Etnia?> = _etnia.asStateFlow()
    val casosFamilia = _casosFamilia.asStateFlow()
    val complicacoesGravidez = _complicacoesGravidez.asStateFlow()
    val prematuro = _prematuro.asStateFlow()

    fun updateNome(novoNome: String) {
        _nome.value = novoNome
    }

    fun updateSobrenome(novoSobrenome: String) {
        _sobrenome.value = novoSobrenome
    }

    fun updateSexo(novoSexo: Sexo) {
        _sexo.value = novoSexo
    }

    fun updateDataNascimento(date: LocalDate) {
        _dataNascimento.value = date
    }

    fun updateEtnia(novaEtnia: Etnia?) {
        if (novaEtnia != null) {
            _etnia.value = novaEtnia
        }
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

    //Responsavel
    private val _nomeResponsavel = MutableStateFlow("")
    private val _sobrenomeResponsavel = MutableStateFlow("")
    private val _parentesco = MutableStateFlow<Parentesco?>(null)
    private val _email = MutableStateFlow("")
    private val _celular = MutableStateFlow("")

    val nomeResponsavel = _nomeResponsavel.asStateFlow()
    val sobrenomeResponsavel = _sobrenomeResponsavel.asStateFlow()
    val parentesco: StateFlow<Parentesco?> = _parentesco.asStateFlow()
    val email = _email.asStateFlow()
    val celular = _celular.asStateFlow()

    fun updateNomeResponsavel(novoNomeResponsavel: String) {
        _nomeResponsavel.value = novoNomeResponsavel
    }

    fun updateSobrenomeResponsavel(novoSobrenomeResponsavel: String) {
        _sobrenomeResponsavel.value = novoSobrenomeResponsavel
    }

    fun updateParentesco(novoParentesco: Parentesco) {
        _parentesco.value = novoParentesco
    }

    fun updateEmail(novoEmail: String) {
        _email.value = novoEmail
    }

    fun updateCelular(novoCelular: String) {
        _celular.value = novoCelular
    }

    suspend fun sendData(): String {
        return if(sexo.value != null && dataNascimento.value != null && etnia.value != null){
            try{
                val responsavel = Responsavel(
                    nome = nomeResponsavel.value,
                    sobrenome = sobrenomeResponsavel.value,
                    parentesco = parentesco.value!!,
                    telefone = celular.value,
                    email = email.value
                )
                val paciente = Paciente(
                    nome = nome.value,
                    sobrenome = sobrenome.value,
                     sexo = sexo.value!!,
                    dataNascimento = dataNascimento.value!!
                        .format(DateTimeFormatter.ofPattern("yyy-MM-dd")),
                    etnia = etnia.value!!,
                    casosFamilia = casosFamilia.value!! == 1,
                    complicacoesGravidez = complicacoesGravidez.value!! == 1,
                    prematuro = prematuro.value!! == 1,
                    responsavel = responsavel
                )
                repository.savePatient(paciente)
                "sucesso"
            }catch(e: Exception){
                e.printStackTrace()
                "Ocorreu um erro ao enviar os dados: ${e.message}"
            }
        }else{
            "Os campos sexo e data de nascimento são obrigatórios."
        }
    }

    fun fillFromPatient(paciente: PacienteModel){
        _pacienteId.value = paciente.id
        _responsavelId.value = paciente.responsavel.id
        _nome.value = paciente.nome
        _sobrenome.value = paciente.sobrenome
        _sexo.value = paciente.sexo
        _dataNascimento.value = LocalDate.parse(paciente.dataNascimento)
        _etnia.value = paciente.etnia
        _casosFamilia.value = if(paciente.casosFamilia) 1 else 0
        _complicacoesGravidez.value = if(paciente.complicacoesGravidez) 1 else 0
        _prematuro.value = if(paciente.prematuro) 1 else 0
        _nomeResponsavel.value = paciente.responsavel.nome
        _sobrenomeResponsavel.value = paciente.responsavel.sobrenome
        _parentesco.value = Parentesco.fromDescricao(paciente.responsavel.parentesco) ?: _parentesco.value
        _email.value = paciente.responsavel.email
        _celular.value = paciente.responsavel.telefone
        _dataCadastro.value = LocalDateTime.parse(paciente.dataCadastro)

        _selectedOptionText.value = paciente.responsavel.parentesco
    }

    suspend fun updatePatient(): String{
        return try{
            val responsavel = ResponsavelModel(
                id = _responsavelId.value,
                nome = nomeResponsavel.value,
                sobrenome = sobrenomeResponsavel.value,
                parentesco = parentesco.value!!.name,
                telefone = celular.value,
                email = email.value
            )
            val paciente = PacienteModel(
                id = _pacienteId.value,
                nome = nome.value,
                sobrenome = sobrenome.value,
                sexo = sexo.value!!,
                dataNascimento = dataNascimento.value!!
                    .format(DateTimeFormatter.ofPattern("yyy-MM-dd")),
                etnia = etnia.value!!,
                casosFamilia = casosFamilia.value!! == 1,
                complicacoesGravidez = complicacoesGravidez.value!! == 1,
                prematuro = prematuro.value!! == 1,
                responsavel = responsavel,
                dataCadastro = _dataCadastro.value!!.toString()
            )
            repository.updatePatient(_pacienteId.value, paciente)
            "sucesso"
        }catch(e: Exception){
            e.printStackTrace()
            Log.i("Erro updateData()", "$e")
            "Ocorreu um erro ao enviar os dados: ${e.message}"
        }
    }
}
