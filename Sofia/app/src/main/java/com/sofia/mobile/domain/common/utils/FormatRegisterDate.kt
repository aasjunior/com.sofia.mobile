package com.sofia.mobile.domain.common.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatRegisterDate(registerDate: String?, locale: Locale, at: String): String {
    return if(registerDate != null){
        val zonedDateTime = ZonedDateTime.parse(registerDate + "Z")
        val languageTag = locale.toLanguageTag()
        val formatter = when (languageTag) {
            "pt-BR" -> DateTimeFormatter.ofPattern("dd/MM/yyyy '${at}' HH'h'mm", locale)
            "en-US" -> DateTimeFormatter.ofPattern("MM/dd/yyyy '${at}' hh:mm a", locale)
            else -> throw IllegalArgumentException("Unsupported language: $languageTag")
        }
        zonedDateTime.format(formatter)
    }else{
        "registerDate retornou nulo"
    }
}
