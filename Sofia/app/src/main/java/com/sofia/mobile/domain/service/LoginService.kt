package com.sofia.mobile.domain.service

import com.sofia.mobile.domain.model.login.LoginRequest
import com.sofia.mobile.domain.model.login.LoginResponse
import com.sofia.mobile.domain.model.login.RefreshRequest
import com.sofia.mobile.domain.model.user.UserRequest
import com.sofia.mobile.domain.model.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/auth/refresh")
    suspend fun refresh(@Body request: RefreshRequest): Response<LoginResponse>

    @POST("/auth/register")
    suspend fun register(@Body request: UserRequest): Response<UserResponse>

    @POST("/auth/checkTokenValidity")
    suspend fun checkTokenValidity(@Body request: RefreshRequest): Response<Boolean>
}