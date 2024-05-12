package com.sofia.mobile.domain.service

import com.aasjunior.mediapickersuite.domain.model.login.LoginRequest
import com.aasjunior.mediapickersuite.domain.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}