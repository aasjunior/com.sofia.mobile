package com.sofia.mobile.domain.common.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatRegisterDate(registerDate: String, locale: Locale): String {
    val zonedDateTime = ZonedDateTime.parse(registerDate)
    val languageTag = locale.toLanguageTag()
    val formatter = when (languageTag) {
        "pt-BR" -> DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH'h'mm", locale)
        "en-US" -> DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a", locale)
        else -> throw IllegalArgumentException("Unsupported language: $languageTag")
    }
    return zonedDateTime.format(formatter)
}