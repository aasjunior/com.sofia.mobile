package com.sofia.mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aasjunior.mediapickersuite.domain.model.login.LoginState
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
    val loginState: StateFlow<LoginState?> by ::_loginState

    fun login(email: String, password: String){
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = authService.authenticate(email, password)
            _loginState.value = result
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
