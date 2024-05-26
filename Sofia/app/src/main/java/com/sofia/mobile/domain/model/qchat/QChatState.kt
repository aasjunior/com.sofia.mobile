package com.sofia.mobile.domain.model.qchat

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class QChatState(
    private val _questions: MutableStateFlow<MutableMap<String, Boolean>> = MutableStateFlow(mutableMapOf())
) {
    val questions: StateFlow<Map<String, Boolean>> by ::_questions

    suspend fun update(question: String, answer: Boolean) {
        _questions.value[question] = answer
        _questions.emit(_questions.value)
    }
}

fun QChatState.toRequest(patientId: String): QChatRequest {
    return QChatRequest(patientId, questions.value)
}