package com.sofia.mobile.domain.model.user

import com.sofia.mobile.domain.common.enums.UserRole
import java.time.LocalDateTime

data class UserResponse(
    val id: String,
    val username: String,
    val email: String,
    val role: UserRole,
    val registrationDate: LocalDateTime
)