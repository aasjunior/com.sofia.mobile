package com.sofia.mobile.config.security

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.sofia.mobile.domain.model.user.UserResponse
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

    override suspend fun saveAccessToken(token: String) {
        sharedPreferences.edit()
            .putString("access_token", token)
            .apply()
    }

    override val accessToken: Flow<String?>
        get() = flow {
            emit(
                sharedPreferences.getString("access_token", null)
            )
        }

    override suspend fun saveRefreshToken(token: String) {
        sharedPreferences.edit()
            .putString("refresh_token", token)
            .apply()
    }

    override val refreshToken: Flow<String?>
        get() = flow {
            emit(
                sharedPreferences.getString("refresh_token", null)
            )
        }

    override suspend fun clearTokens() {
        sharedPreferences.edit()
            .remove("access_token")
            .remove("refresh_token")
            .remove("user")
            .apply()
    }

    fun saveUser(user: UserResponse){
        val userJson = Gson().toJson(user)
        Log.i("SaveUserJson", "$userJson")
        sharedPreferences.edit()
            .putString("user", userJson)
            .apply()
    }

    val user: Flow<UserResponse?>
        get() = flow {
            val userJson = sharedPreferences.getString("user", null)
            val user =
                if(userJson != null) Gson().fromJson(userJson, UserResponse::class.java)
                else null
        }

    fun getUser(): UserResponse?{
        val userJson = sharedPreferences.getString("user", null)
        val user = if(userJson != null) Gson().fromJson(userJson, UserResponse::class.java) else null
        Log.i("GetUser", "$user")
        return user
    }
}