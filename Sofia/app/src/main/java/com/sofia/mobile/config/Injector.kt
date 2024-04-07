package com.sofia.mobile.config

import android.content.Context
import com.sofia.mobile.config.preferences.language.LanguageManager

object Injector {
    private lateinit var languageManager: LanguageManager

    fun initialize(context: Context){
        languageManager = LanguageManager(context.applicationContext)
    }

    fun provideLanguageManager(): LanguageManager = languageManager
}