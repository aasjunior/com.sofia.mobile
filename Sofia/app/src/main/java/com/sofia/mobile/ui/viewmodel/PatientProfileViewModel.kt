package com.sofia.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.domain.checklist.TestResponse
import com.sofia.mobile.domain.model.patient.Patient

class PatientProfileViewModel: ViewModel(){
    private val patientRepository = ApiClient.patientRepository
    private val qChatRepository = ApiClient.qchatRepository

    private val _patient = mutableStateOf<Patient?>(null)
    private val _testResponse = mutableStateOf<TestResponse?>(null)

    val patient: Patient?
        get() = _patient.value

    val testResponse: TestResponse?
        get() = _testResponse.value

    val errorMessage = mutableStateOf<String?>(null)

    suspend fun fetchPatient(id: String){
        try {
            _patient.value = patientRepository.getPatient(id)
        }catch(e: Exception){
            errorMessage.value = e.message
            Log.i("FetchPatientError", e.message!!)
        }
    }

    suspend fun fetchTest(patientId: String){
        try {
            val response = qChatRepository.getQChat(patientId)
            if(response.isSuccessful){
                _testResponse.value = response.body()
            }else{
                errorMessage.value = "Error fetching test: ${response.errorBody()?.string()}"
            }
        }catch(e: Exception){
            errorMessage.value = e.message
            Log.i("FetchTestError", e.message!!)
        }
    }
}