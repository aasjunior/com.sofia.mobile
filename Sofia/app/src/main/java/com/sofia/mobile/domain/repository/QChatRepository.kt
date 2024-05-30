package com.sofia.mobile.domain.repository

import com.sofia.mobile.domain.checklist.TestResponse
import com.sofia.mobile.domain.checklist.qchat.QChatRequest
import com.sofia.mobile.domain.checklist.qchat.QChatResponse
import com.sofia.mobile.domain.service.QChatService
import retrofit2.Response

class QChatRepository(private val service: QChatService) {
    suspend fun submit(request: QChatRequest): QChatResponse {
        return service.submitQChat(request)
    }

    suspend fun getQChat(patientId: String): Response<TestResponse>{
        return service.getQChat(patientId)
    }
}