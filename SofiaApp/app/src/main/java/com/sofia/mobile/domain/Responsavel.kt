package com.sofia.mobile.domain

data class Responsavel(
    val nome: String,
    val sobrenome: String,
    val parentesco: Parentesco,
    val telefone: String,
    val email: String
)
