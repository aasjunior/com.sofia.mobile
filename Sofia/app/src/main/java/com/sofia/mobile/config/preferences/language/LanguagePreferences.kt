package com.sofia.mobile.config.preferences.language

import android.content.Context

class LanguagePreferences(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences(
        "language_prefs",
        Context.MODE_PRIVATE
    )

    fun saveLanguage(language: Language){
        sharedPreferences.edit()
            .putString("language", language.name)
            .apply()
    }

    val language: Language?
        get() {
            val languageCode = sharedPreferences.getString("language", null)
            return Language.values().find { it.name == languageCode }
        }
}