package com.sofia.mobile.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofia.mobile.domain.model.login.LoginState
import com.sofia.mobile.config.Injector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val authService = Injector.provideAuthService()
    private val securePreferences = Injector.provideSecurePreferences()

    private val _loginState = MutableStateFlow<LoginState?>(null)
    val isFirstLogin = mutableStateOf(false)

    val loginState: StateFlow<LoginState?> by ::_loginState

    val errorMessage = MutableStateFlow<String?>(null)

    fun updateErrrorMessage(e: String){
        this.errorMessage.value = e
    }
    fun login(email: String, password: String){
        viewModelScope.launch {
            try{
                _loginState.value = LoginState.Loading
                val result: LoginState = authService.authenticate(email, password)
                when(result){
                    is LoginState.Success -> {
                        _loginState.value = result
                    }
                    is LoginState.Error -> {
                        _loginState.value = result
                        errorMessage.value = result.error
                    }
                    else -> throw Exception("Erro login")
                }
            }catch(e: Exception){
                errorMessage.value = e.message
            }
        }
    }


    fun firstLogin(email: String, password: String){
        viewModelScope.launch {
            val result = authService.authenticate(email, password)
            isFirstLogin.value = true
        }
    }

    fun isLoggedIn(): Flow<Boolean>{
        return securePreferences.accessToken.map { it != null }
    }

    fun logout(){
        viewModelScope.launch {
            authService.logout()
        }
    }
}
