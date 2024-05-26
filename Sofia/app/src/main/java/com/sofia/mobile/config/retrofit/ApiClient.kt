package com.sofia.mobile.config.retrofit

import com.sofia.mobile.domain.repository.PatientRepository
import com.sofia.mobile.domain.repository.QChatRepository
import com.sofia.mobile.domain.service.LoginService
import com.sofia.mobile.domain.service.PatientService
import com.sofia.mobile.domain.service.QChatService

object ApiClient {
    val loginService: LoginService by lazy {
        RetrofitClient.retrofit.create(LoginService::class.java)
    }
    private val patientService: PatientService by lazy {
        RetrofitClient.retrofit.create(PatientService::class.java)
    }
    val patientRepository: PatientRepository by lazy {
        PatientRepository(patientService)
    }

    private val qchatService: QChatService by lazy {
        RetrofitClient.retrofit.create(QChatService::class.java)
    }
    val qchatRepository: QChatRepository by lazy {
        QChatRepository(qchatService)
    }
}