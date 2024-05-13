package com.sofia.mobile.config

import android.content.Context
import com.sofia.mobile.config.preferences.language.LanguageManager
import com.sofia.mobile.config.security.SecurePreferences
import com.sofia.mobile.domain.service.AuthenticationService

object Injector {
    private lateinit var languageManager: LanguageManager
    private lateinit var securePreferences: SecurePreferences
    private lateinit var authService: AuthenticationService

    fun initialize(context: Context){
        securePreferences = SecurePreferences(context)
        authService = AuthenticationService(securePreferences)
        languageManager = LanguageManager(context.applicationContext)
    }

    fun provideLanguageManager(): LanguageManager = languageManager
    fun provideSecurePreferences(): SecurePreferences = securePreferences
    fun provideAuthService(): AuthenticationService = authService
}