package com.aasjunior.mediapickersuite.domain.model.login

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)