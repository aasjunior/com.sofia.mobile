package com.sofia.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sofia.mobile.config.Injector
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.domain.model.user.UserRequest
import com.sofia.mobile.domain.model.user.UserResponse
import com.sofia.mobile.domain.model.user.UserState
import com.sofia.mobile.domain.model.user.toRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class UserViewModel: ViewModel() {
    private val loginService = ApiClient.loginService
    private val securePreferences = Injector.provideSecurePreferences()

    private val _userState = MutableStateFlow(UserState())
    private val _userResponse = MutableStateFlow<UserResponse?>(null)

    val userState: StateFlow<UserState> by ::_userState
    val errorMessage = mutableStateOf<String?>(null)

    val user: StateFlow<UserResponse?> = _userResponse

    suspend fun sendData(): String{
        return try{
            _userState.value.updateUsername()
            val request: UserRequest = _userState.value.toRequest()

            if(request.isNotEmptyFields()){
                val response = loginService.register(request)
                if(response.isSuccessful){
                    this._userResponse.value = response.body()
                    "success"
                }else{
                    throw Exception(response.message())
                }
            }else{
                throw Exception("Empty shields: $request")
            }
        }catch(e: Exception){
            Log.i("SendDataError", e.message!!)
            e.message!!
        }
    }

    fun getUser(){
        try {
            _userResponse.value = securePreferences.getUser()
        }catch(e: Exception){
            Log.i("GetUser", e.message!!)
            errorMessage.value = e.message
        }
    }

}