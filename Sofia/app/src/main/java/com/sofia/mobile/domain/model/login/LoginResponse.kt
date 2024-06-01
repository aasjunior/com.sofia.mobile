package com.sofia.mobile.domain.model.login

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)