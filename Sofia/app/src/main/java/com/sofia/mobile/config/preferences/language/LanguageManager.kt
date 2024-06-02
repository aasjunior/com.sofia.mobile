package com.sofia.mobile.config.preferences.language

import android.content.Context
import android.content.res.Configuration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class LanguageManager(private val context: Context) {
    private val languagePreferences = LanguagePreferences(context)

    private val _currentLanguage = MutableStateFlow(languagePreferences.language ?: Language.PT_BR)
    val currentLanguage: StateFlow<Language> = _currentLanguage

    fun updateLanguage(value: Language) {
        languagePreferences.saveLanguage(value)
        _currentLanguage.value = value
    }

    fun updateResources(locale: Locale): Context{
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }
}