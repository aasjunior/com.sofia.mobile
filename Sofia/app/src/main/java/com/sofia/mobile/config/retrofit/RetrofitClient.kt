package com.sofia.mobile.config.retrofit

import com.sofia.mobile.config.Injector
import com.sofia.mobile.config.security.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080"

    val retrofit: Retrofit by lazy {
        val securePreferences = Injector.provideSecurePreferences()
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(AuthInterceptor(securePreferences))
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}