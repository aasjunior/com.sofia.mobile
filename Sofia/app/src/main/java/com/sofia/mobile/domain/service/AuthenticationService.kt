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
                response.body()?.let { loginResponse ->
                    securePreferences.saveAccessToken(loginResponse.accessToken)
                    securePreferences.saveRefreshToken(loginResponse.refreshToken)
                    saveUser(loginResponse.userId)
                    LoginState.Success(loginResponse.accessToken)
                } ?: run {
                    LoginState.Error("Tokens are null")
                }
            }else{
                val errorBody = response.errorBody()?.string()
                Log.i("ErroAuthenticate", "$errorBody")
                LoginState.Error("Usuário ou senha incorreto")
            }
        }catch(e: Exception){
            Log.i("ErroAuthenticateException", "$e")
            LoginState.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun refresh(): LoginState {
        val refreshToken = runBlocking { securePreferences.refreshToken.first() }
        if(refreshToken != null) {
            return try{
                val response = apiClient.refresh(
                    RefreshRequest(refreshToken)
                )
                if(response.isSuccessful){
                    response.body()?.let { newTokens ->
                        if(newTokens.accessToken != null && newTokens.refreshToken != null){
                            securePreferences.saveAccessToken(newTokens.accessToken)
                            securePreferences.saveRefreshToken(newTokens.refreshToken)
                            LoginState.Success(newTokens.accessToken)
                        }else{
                            LoginState.Error("New tokens are null")
                        }
                    } ?: LoginState.Error("Response body is null")
                }else{
                    val errorBody = response.errorBody()?.string()
                    LoginState.Error(errorBody ?: "Unknown error")
                }
            }catch(e: Exception){
                LoginState.Error(e.message ?: "Unknown error")
            }

        } else {
            return LoginState.Error("Refresh token is null")
        }
    }

    private suspend fun saveUser(userId: String) {
        val response = apiClient.getUserById(userId)
        Log.i("SaveUserAuth", "$response")
        if(response.isSuccessful){
            response.body()?.let { userResponse ->

                Log.i("SaveUserAuth", "$userResponse")
                securePreferences.saveUser(userResponse)
            }
        }
    }

    suspend fun logout(){
        securePreferences.clearTokens()
    }

}
