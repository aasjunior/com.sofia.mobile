package com.aasjunior.mediapickersuite.domain.model.login

sealed class LoginState{
    object Loading: LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val error: String) : LoginState()
}
