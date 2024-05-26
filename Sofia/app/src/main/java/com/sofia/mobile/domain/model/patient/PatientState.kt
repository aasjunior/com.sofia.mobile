package com.sofia.mobile.domain.model.patient

import com.sofia.mobile.domain.common.enums.Ethnicity
import com.sofia.mobile.domain.common.enums.Gender
import com.sofia.mobile.domain.model.guardian.GuardianState
import com.sofia.mobile.domain.model.person.PersonState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class PatientState(
    private val _gender: MutableStateFlow<Gender?> = MutableStateFlow(null),
    private val _birthDate: MutableStateFlow<String> = MutableStateFlow(""),
    private val _ethnicity: MutableStateFlow<Ethnicity?> = MutableStateFlow(null),
    private val _familyCases: MutableStateFlow<Boolean?> = MutableStateFlow(null),
    private val _pregnancyComplications: MutableStateFlow<Boolean?> = MutableStateFlow(null),
    private val _premature: MutableStateFlow<Boolean?> = MutableStateFlow(null),
    private val _guardians: MutableStateFlow<List<GuardianState>?> = MutableStateFlow(null)
): PersonState(){
    val gender: StateFlow<Gender?> by ::_gender
    val birthDate: StateFlow<String> by ::_birthDate
    val ethnicity: StateFlow<Ethnicity?> by ::_ethnicity
    val familyCases: StateFlow<Boolean?> by ::_familyCases
    val pregnancyComplications: StateFlow<Boolean?> by ::_pregnancyComplications
    val premature: StateFlow<Boolean?> by ::_premature
    val guardian: StateFlow<List<GuardianState>?> by ::_guardians

    fun updateGender(newGender: Gender){
        this._gender.value = newGender
    }

    fun updateBirthDate(newBirthDate: String){
        this._birthDate.value = newBirthDate
    }

    fun updateEthnicity(newEthnicity: Ethnicity?){
        this._ethnicity.value = newEthnicity
    }

    fun updateFamilyCases(newFamilyCases: Boolean?){
        this._familyCases.value = newFamilyCases
    }

    fun updatePregnancyComplications(newPregnancyComplications: Boolean?){
        this._pregnancyComplications.value = newPregnancyComplications
    }

    fun updatePremature(newPremature: Boolean?){
        this._premature.value = newPremature
    }

    fun updateGuardians(newGuardians: List<GuardianState>){
        this._guardians.value = newGuardians
    }

    fun newGuardian(newGuardian: GuardianState){
        val currentGuardians = _guardians.value ?: emptyList()
        this._guardians.value = currentGuardians + newGuardian
    }
}

fun PatientState.toRequest(): PatientRequest{
    return PatientRequest(
        firstName = this.firstName.value,
        lastName = this.lastName.value,
        birthDate = this.birthDate.value,
        gender = this.gender.value!!,
        ethnicity = this.ethnicity.value!!,
        familyCases = this.familyCases.value!!,
        pregnancyComplications = this.pregnancyComplications.value!!,
        premature = this.premature.value!!
    )
}