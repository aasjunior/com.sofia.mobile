package com.sofia.mobile.domain.model.user

import com.sofia.mobile.domain.common.enums.UserRole

data class UserRequest(
    val username: String,
    val email: String,
    val password: String,
    val role: UserRole
)
