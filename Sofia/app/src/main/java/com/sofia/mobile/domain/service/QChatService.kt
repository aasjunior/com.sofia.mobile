package com.sofia.mobile.domain.service

import com.sofia.mobile.domain.model.qchat.QChatRequest
import com.sofia.mobile.domain.model.qchat.QChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface QChatService {
    @POST("/checklist/qchat/submit")
    suspend fun submitQChat(@Body request: QChatRequest): QChatResponse
}