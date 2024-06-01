package com.sofia.mobile.domain.service

import android.util.Log
import com.sofia.mobile.domain.model.login.LoginRequest
import com.sofia.mobile.domain.model.login.LoginState
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.config.security.SecurePreferences
import com.sofia.mobile.domain.model.login.RefreshRequest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class AuthenticationService(
    private val securePreferences: SecurePreferences
) {
    private val apiClient = ApiClient.loginService

    suspend fun authenticate(username: String, password: String): LoginState {
        return try{
            val response = apiClient.login(
                LoginRequest(username, password)
            )

            if(response.isSuccessful){
                Log.i("Response.isSuccessful", "$response")
                response.body()?.let { loginResponse ->
                    securePreferences.saveAccessToken(loginResponse.accessToken)
                    securePreferences.saveRefreshToken(loginResponse.refreshToken)
                    Log.i("Refresh Token", loginResponse.refreshToken)
                    LoginState.Success(loginResponse.accessToken)
                } ?: run {
                    Log.e("AuthenticationService", "Tokens are null")
                    LoginState.Error("Tokens are null")
                }
            }else{
                val errorBody = response.errorBody()?.string()
                Log.e("AuthenticationService", errorBody ?: "Unknown error")
                LoginState.Error(errorBody ?: "Unknown error")
            }
        }catch(e: Exception){
            Log.e("AuthenticationService", e.message ?: "Unknown error")
            LoginState.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun refresh(): LoginState {
        val refreshToken = runBlocking { securePreferences.refreshToken.first() }
        Log.e("AuthenticationService", "Refresh called with refreshToken $refreshToken")
        if(refreshToken != null) {
            return try{
                val response = apiClient.refresh(
                    RefreshRequest(refreshToken)
                )
                Log.e("AuthenticationService", "Called apiClient.refresh, response is $response")
                if(response.isSuccessful){
                    Log.i("Response.isSuccessful", "$response")
                    Log.e("AuthenticationService", "Refresh result: ${response.body()?.accessToken}")
                    response.body()?.let { newTokens ->
                        if(newTokens.accessToken != null && newTokens.refreshToken != null){
                            securePreferences.saveAccessToken(newTokens.accessToken)
                            securePreferences.saveRefreshToken(newTokens.refreshToken) // Save the new refresh token
                            LoginState.Success(newTokens.accessToken)
                        }else{
                            Log.e("AuthenticationService", "New tokens are null")
                            LoginState.Error("New tokens are null")
                        }
                    } ?: LoginState.Error("Response body is null")
                }else{
                    val errorBody = response.errorBody()?.string()
                    Log.e("AuthenticationService", errorBody ?: "Unknown error")
                    LoginState.Error(errorBody ?: "Unknown error")
                }
            }catch(e: Exception){
                Log.e("AuthenticationService", "Exception during refresh: $e")
                Log.e("AuthenticationService", e.message ?: "Unknown error")
                LoginState.Error(e.message ?: "Unknown error")
            }

        } else {
            Log.e("AuthenticationService", "Refresh token is null")
            return LoginState.Error("Refresh token is null")
        }
    }



    suspend fun logout(){
        securePreferences.clearTokens()
    }

}
