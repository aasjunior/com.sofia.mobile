package com.sofia.mobile.domain.model.user

import com.sofia.mobile.domain.common.enums.UserRole
import java.time.LocalDateTime

data class UserResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val role: UserRole,
    val registrationDate: String
)

fun UserResponse.toState(): UserState{
    val userState = UserState()
    userState.updateFirstName(this.firstName)
    userState.updateLastName(this.lastName)
    userState.updateUsername()
    userState.updateEmail(this.email)
    userState.updateUserRole(this.role)

    return userState
}