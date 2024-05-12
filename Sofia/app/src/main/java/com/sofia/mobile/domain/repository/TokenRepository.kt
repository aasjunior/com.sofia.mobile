package com.sofia.mobile.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveToken(token: String)
    val token: Flow<String?>

    suspend fun clearToken()
}