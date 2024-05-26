package com.sofia.mobile.domain.model.qchat

data class QChatRequest(
    val patientId: String,
    val questions: Map<String, Boolean>
){
    fun createQChatRequest(patientId: String, questions: Map<String, Boolean>): QChatRequest? {
        return if(questions.size == 10) {
            QChatRequest(patientId, questions)
        } else {
            null
        }
    }
}
