package com.sofia.mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aasjunior.mediapickersuite.domain.model.login.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _loginState = MutableStateFlow<LoginState?>(null)
    val loginState: StateFlow<LoginState?> by ::_loginState

    fun login(email: String, password: String){
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
        }
    }
}