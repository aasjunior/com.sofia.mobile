package com.sofia.mobile.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveAccessToken(token: String)
    val accessToken: Flow<String?>
    suspend fun saveRefreshToken(token: String)
    val refreshToken: Flow<String?>
    suspend fun clearTokens()
}