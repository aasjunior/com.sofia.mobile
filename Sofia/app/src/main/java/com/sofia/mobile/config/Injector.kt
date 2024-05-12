package com.sofia.mobile.config

import android.content.Context
import com.sofia.mobile.config.preferences.language.LanguageManager
import com.sofia.mobile.config.security.SecurePreferences

object Injector {
    private lateinit var languageManager: LanguageManager
    private lateinit var securePreferences: SecurePreferences

    fun initialize(context: Context){
        securePreferences = SecurePreferences(context)
        languageManager = LanguageManager(context.applicationContext)
    }

    fun provideLanguageManager(): LanguageManager = languageManager
    fun provideSecurePreferences(): SecurePreferences = securePreferences
}