package com.sofia.mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sofia.mobile.domain.model.guardian.GuardianState
import com.sofia.mobile.domain.model.patient.PatientState
import com.sofia.mobile.domain.model.patientguardian.PatientGuardianState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PatientViewModel : ViewModel() {
    private val _patientState = MutableStateFlow(PatientState())
    private val _guardianState = MutableStateFlow(GuardianState())
    private val _patientGuardianState = MutableStateFlow(PatientGuardianState())

    val patientState: StateFlow<PatientState> by ::_patientState
    val guardianState: StateFlow<GuardianState> by ::_guardianState
    val patientGuardianState: StateFlow<PatientGuardianState> by ::_patientGuardianState
}