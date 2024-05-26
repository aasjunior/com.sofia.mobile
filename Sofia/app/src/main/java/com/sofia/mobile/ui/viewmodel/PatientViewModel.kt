package com.sofia.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.domain.common.enums.Kinship
import com.sofia.mobile.domain.model.guardian.GuardianState
import com.sofia.mobile.domain.model.guardian.toRequest
import com.sofia.mobile.domain.model.patient.PatientState
import com.sofia.mobile.domain.model.patient.toRequest
import com.sofia.mobile.domain.model.patientguardian.PatientGuardianRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PatientViewModel : ViewModel() {
    private val patientRepository = ApiClient.patientRepository

    private val _patientState = MutableStateFlow(PatientState())
    private val _guardianState = MutableStateFlow(GuardianState())
    private val _kinship = MutableStateFlow<Kinship?>(null)

    val patientState: StateFlow<PatientState> by ::_patientState
    val guardianState: StateFlow<GuardianState> by ::_guardianState
    val kinship: StateFlow<Kinship?> by ::_kinship

    val errorMessage = mutableStateOf<String?>(null)

    fun updateKinship(newKinship: Kinship){
        this._kinship.value = newKinship
    }

    suspend fun sendData(): String{
        return try {
            val request = PatientGuardianRequest(
                _patientState.value.toRequest(),
                _guardianState.value.toRequest(),
                kinship.value!!
            )

            val patient = patientRepository.savePatientWithGuardian(request)
            "success"
        }catch(e: Exception){
            e.printStackTrace()
            Log.i("Error sendData()", "$e")
            "${errorMessage.value} ${e.message}"
        }
    }

    fun updatePatient(): String{
        return try{
            ""
        }catch(e: Exception){
            "${e.message}"
        }
    }
}