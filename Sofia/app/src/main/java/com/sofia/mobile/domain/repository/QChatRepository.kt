package com.sofia.mobile.domain.repository

import com.sofia.mobile.domain.model.qchat.QChatRequest
import com.sofia.mobile.domain.model.qchat.QChatResponse
import com.sofia.mobile.domain.service.QChatService

class QChatRepository(private val service: QChatService) {
    suspend fun submit(request: QChatRequest): QChatResponse{
        return service.submitQChat(request)
    }
}