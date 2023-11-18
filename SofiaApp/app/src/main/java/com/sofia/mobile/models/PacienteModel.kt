package com.sofia.mobile.models

import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.domain.Sexo
import java.time.LocalDate
import java.time.LocalDateTime

data class PacienteModel(
    val id: Long,
    val nome: String,
    val sobrenome: String,
    val sexo: Sexo,
    val dataNascimento: LocalDate,
    val etnia: Etnia,
    val casosFamilia: Boolean,
    val complicacoesGravidez: Boolean,
    val prematuro: Boolean,
    val responsavel: ResponsavelModel,
    val dataCadastro: LocalDateTime
)