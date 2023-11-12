package com.sofia.mobile.domain

import java.time.LocalDate

data class Paciente(
    val nome: String,
    val sobrenome: String,
    val sexo: Sexo,
    val dataNascimento: String,
    /*
    val etnia: Etnia,
    val sexo: Sexo,
    */
)
