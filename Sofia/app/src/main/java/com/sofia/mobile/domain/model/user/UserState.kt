package com.sofia.mobile.domain.model.user

import com.sofia.mobile.domain.common.enums.UserRole
import com.sofia.mobile.domain.model.person.PersonState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class UserState(
    private val _username: MutableStateFlow<String> = MutableStateFlow(""),
    private val _email: MutableStateFlow<String> = MutableStateFlow(""),
    private val _password: MutableStateFlow<String> = MutableStateFlow(""),
    private val _role: MutableStateFlow<UserRole> = MutableStateFlow(UserRole.USER)
): PersonState(){
    val username: StateFlow<String> by ::_username
    val email: StateFlow<String> by ::_email
    val password: StateFlow<String> by ::_password
    val role: StateFlow<UserRole?> by ::_role

    fun updateUsername(){
        this._username.value = "${this.firstName.value}.${this.lastName.value}"
    }

    fun updateEmail(newEmail: String){
        this._email.value = newEmail
    }

    fun updatePassword(newPassword: String){
        this._password.value = newPassword
    }

    fun updateUserRole(newRole: UserRole){
        this._role.value = newRole
    }
}

fun UserState.toRequest(): UserRequest{
    return UserRequest(
        firstName.value,
        lastName.value,
        username.value,
        email.value,
        password.value,
        role.value!!
    )
}