package com.sofia.mobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.domain.model.qchat.QChatState
import com.sofia.mobile.domain.model.qchat.toRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QChatViewModel(patientId: String): ViewModel() {
    private val repository = ApiClient.qchatRepository
    private val patientId = patientId

    private val _qchatState = MutableStateFlow(QChatState())

    val qChatState: StateFlow<QChatState> by ::_qchatState

    fun update(question: String, answer: Boolean) {
        val currentQuestions = _qchatState.value.questions.value.toMutableMap()
        currentQuestions[question] = answer
        _qchatState.value = QChatState(MutableStateFlow(currentQuestions))
    }

    suspend fun submit(){
        try{
            val request = _qchatState.value.toRequest(this.patientId)
            val response = repository.submit(request)

        }catch(e: Exception){
            e.printStackTrace()
            Log.i("Error submit()", "${e.message}")
        }
    }
}