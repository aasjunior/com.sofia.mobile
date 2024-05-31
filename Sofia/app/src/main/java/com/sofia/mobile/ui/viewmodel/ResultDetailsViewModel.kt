package com.sofia.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.domain.checklist.qchat.QChat
import com.sofia.mobile.domain.model.patient.Patient
import kotlinx.coroutines.launch

class ResultDetailsViewModel: ViewModel() {
    private val qChatRepository = ApiClient.qchatRepository
    private val patientRepository = ApiClient.patientRepository

    private val _qChatResponses = mutableStateOf<QChat?>(null)
    private val _patient = mutableStateOf<Patient?>(null)
    val errorMessage = mutableStateOf<String?>(null)

    val qChatResponses: QChat?
        get() = _qChatResponses.value

    val patient: Patient?
        get() = _patient.value
    suspend fun fetchQChat(testId: String){
        try{
           val responses = qChatRepository.getChatResponses(testId)
           if(responses.isSuccessful){
               _qChatResponses.value = responses.body()

               Log.i("FetchQChatError", "${_qChatResponses.value}")
               viewModelScope.launch {
                    fetchPatient(_qChatResponses.value!!.patientId)
               }
           }else{
               throw Exception("Not Found")
           }
        }catch(e: Exception){
            Log.i("FetchQChatExceptionError", e.message!!)
            errorMessage.value = e.message
        }
    }

    private suspend fun fetchPatient(patientId: String){
        try {
            _patient.value = patientRepository.getPatient(patientId)
            Log.i("FetchPatientError", "${_patient.value}")
        }catch(e: Exception){
            errorMessage.value = e.message
            Log.i("FetchPatientError", e.message!!)
        }
    }
}