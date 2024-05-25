package com.sofia.mobile.domain.model.guardian

data class GuardianRequest(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String
)