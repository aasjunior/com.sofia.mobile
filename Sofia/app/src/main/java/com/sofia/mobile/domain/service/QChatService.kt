package com.sofia.mobile.domain.service

import com.sofia.mobile.domain.checklist.TestResponse
import com.sofia.mobile.domain.checklist.qchat.QChatRequest
import com.sofia.mobile.domain.checklist.qchat.QChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QChatService {
    @POST("/checklist/qchat/submit")
    suspend fun submitQChat(@Body request: QChatRequest): QChatResponse

    @GET("/checklist/qchat/{patientId}")
    suspend fun getQChat(@Path("patientId") patientId: String): Response<TestResponse>
}