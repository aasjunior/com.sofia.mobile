package com.sofia.mobile.domain.model.user

import com.sofia.mobile.domain.common.enums.UserRole

data class UserRequest(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val role: UserRole
){
    fun isNotEmptyFields(): Boolean{
        return this.firstName.isNotEmpty() &&
                this.lastName.isNotEmpty() &&
                this.username.isNotEmpty() &&
                this.email.isNotEmpty() &&
                this.password.isNotEmpty() &&
                this.role != null
    }
}
