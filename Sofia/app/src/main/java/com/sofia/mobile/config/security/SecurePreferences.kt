package com.sofia.mobile.config.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.sofia.mobile.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SecurePreferences(context: Context): TokenRepository {
    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKeyAlias,
        EncryptedSharedPreferences
            .PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences
            .PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString("token", token)
            .apply()
    }

    override val token: Flow<String?>
        get() = flow {
            emit(
                sharedPreferences.getString("token", null)
            )
        }

    override suspend fun clearToken() {
        sharedPreferences.edit()
            .remove("token")
            .apply()
    }
}