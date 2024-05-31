package com.sofia.mobile.domain.checklist.qchat


data class QChat(
    val id: String,
    val patientId: String,
    val questions: Map<String, Boolean>,
    val registerDate: String,
    val responses: QChatResponse
)
