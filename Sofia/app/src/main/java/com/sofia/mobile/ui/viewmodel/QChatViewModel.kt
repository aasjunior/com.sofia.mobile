package com.sofia.mobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sofia.mobile.R
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.domain.model.qchat.QChatResponse
import com.sofia.mobile.domain.model.qchat.QChatState
import com.sofia.mobile.domain.model.qchat.toRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QChatViewModel(private val patientId: String): ViewModel() {
    private val repository = ApiClient.qchatRepository

    private val _response = MutableStateFlow<QChatResponse?>(null)
    private val _qchatState = MutableStateFlow(QChatState())
    private val _errorMessage = MutableStateFlow<String?>(null)

    val response: StateFlow<QChatResponse?> by ::_response
    val qChatState: StateFlow<QChatState> by ::_qchatState
    val errorMessage: StateFlow<String?> by ::_errorMessage

    fun update(question: String, answer: Boolean) {
        val currentQuestions = _qchatState.value.questions.value.toMutableMap()
        currentQuestions[question] = answer
        _qchatState.value = QChatState(MutableStateFlow(currentQuestions))
    }

    suspend fun submit(): String{
        return try{
            val request = _qchatState.value.toRequest(this.patientId)

            this._response.value = repository.submit(request)
            Log.i("QChat Response", "${_response.value}")
            "success"
        }catch(e: Exception){
            e.printStackTrace()
            Log.i("Error submit()", "${e.message}")
            "${e.message}"
        }
    }

    fun getAccuracy(): String{
        return "${(_response.value!!.accuracy * 100).toInt()}"
    }

    fun getResult(): Int{
        return if(_response.value!!.result) R.string.result_positive else R.string.result_negative
    }
}