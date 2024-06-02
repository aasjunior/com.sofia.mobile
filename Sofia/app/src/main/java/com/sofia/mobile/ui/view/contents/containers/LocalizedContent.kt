package com.sofia.mobile.ui.view.contents.containers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.sofia.mobile.R
import com.sofia.mobile.config.Injector
import com.sofia.mobile.config.preferences.language.LanguageManager

@Composable
fun LocalizedContent(
    content: @Composable () -> Unit
){
    val context = LocalContext.current
    val LocalLanguageManager = compositionLocalOf<LanguageManager> {
        error(
            context.getString(R.string.error_no_localizedcontext)
        )
    }
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