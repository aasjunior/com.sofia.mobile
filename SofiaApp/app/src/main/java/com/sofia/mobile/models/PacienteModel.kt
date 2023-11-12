package com.sofia.mobile.models

import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.domain.Sexo
import java.time.LocalDate

class PacienteModel(
    private val nome: String,
    private val dataNascimento: LocalDate,
    private val etnia: Etnia,
    private val sexo: Sexo,
    private val casosFamilia: Boolean,
    private val ComplicacaoGravidez: Boolean,
    private val prematuro: Boolean
){
    fun getNome(): String{
        return this.nome
    }

}