package com.sofia.mobile.ui.view.contents.containers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sofia.mobile.config.Injector

@Composable
fun LocalizedContent(
    content: @Composable () -> Unit
){
    val languageManager = remember {
        Injector.provideLanguageManager()
    }
    val currentLanguage = languageManager.currentLanguage.collectAsState()

    CompositionLocalProvider(
        LocalContext provides languageManager
            .updateResources(currentLanguage.value.locale)
    ) {
        content()
    }
}