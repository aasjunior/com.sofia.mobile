package com.sofia.mobile.domain

enum class Parentesco(val descricao: String){
    MAE("Mãe"),
    PAI("Pai"),
    IRMA("Irmã"),
    IRMAO("Irmão"),
    TIA("Tia"),
    TIO("Tio"),
    AVO1("Avó"),
    AVO2("Avô");

    companion object {
        fun fromDescricao(descricao: String): Parentesco? {
            return values().find { it.descricao == descricao }
        }
    }
}