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

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> by ::_errorMessage

    fun updateErrrorMessage(e: String){
        this._errorMessage.value = e
    }
    fun login(email: String, password: String){
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = authService.authenticate(email, password)
            _loginState.value = result
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
