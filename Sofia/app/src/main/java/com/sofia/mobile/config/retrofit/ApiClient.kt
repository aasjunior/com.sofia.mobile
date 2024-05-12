package com.sofia.mobile.config.retrofit

import com.sofia.mobile.domain.service.LoginService

object ApiClient {
    val loginService: LoginService by lazy {
        RetrofitClient.retrofit.create(LoginService::class.java)
    }
}