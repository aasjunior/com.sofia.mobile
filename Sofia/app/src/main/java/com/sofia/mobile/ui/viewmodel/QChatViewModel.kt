package com.sofia.mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sofia.mobile.domain.model.qchat.QChatState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QChatViewModel(patientId: String): ViewModel() {
    private val _qchatState = MutableStateFlow(QChatState())

    val qChatState: StateFlow<QChatState> by ::_qchatState

    fun update(question: String, answer: Boolean) {
        val currentQuestions = _qchatState.value.questions.value.toMutableMap()
        currentQuestions[question] = answer
        _qchatState.value = QChatState(MutableStateFlow(currentQuestions))
    }

}