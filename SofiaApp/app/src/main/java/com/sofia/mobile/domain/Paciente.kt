package com.sofia.mobile.domain

data class Paciente(
    val nome: String,
    val sobrenome: String,
    val sexo: Sexo,
    val dataNascimento: String,
    val etnia: Etnia
)
