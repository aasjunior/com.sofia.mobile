package com.sofia.mobile.config.security

import android.util.Log
import com.aasjunior.mediapickersuite.domain.model.login.LoginState
import com.sofia.mobile.config.Injector
import com.sofia.mobile.domain.service.AuthenticationService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val securePreferences: SecurePreferences
): Interceptor {
    private val authService: AuthenticationService by lazy { Injector.provideAuthService() }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val accessToken = runBlocking { securePreferences.accessToken.first() }
        if(accessToken != null){
            val newRequest = originalRequest
                .newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
            val response = chain.proceed(newRequest)
            if(response.code == 401 || response.code == 403){
                Log.e("AuthInterceptor", "${response.code}")
                val refreshResponse = runBlocking { authService.refresh() }
                if(refreshResponse is LoginState.Success){
                    val refreshedToken = refreshResponse.token
                    Log.e("RefreshToken", refreshedToken)
                    runBlocking { securePreferences.saveAccessToken(refreshedToken) }
                    val newRequestWithRefreshedToken = originalRequest
                        .newBuilder()
                        .header("Authorization", "Bearer $refreshedToken")
                        .build()
                    return chain.proceed(newRequestWithRefreshedToken)
                }
            }
            return response
        }
        return chain.proceed(originalRequest)
    }
}
