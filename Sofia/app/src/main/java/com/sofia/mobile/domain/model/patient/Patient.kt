package com.sofia.mobile.domain.model.patient

import com.sofia.mobile.domain.common.enums.Ethnicity
import com.sofia.mobile.domain.common.enums.Gender
import com.sofia.mobile.domain.model.guardian.Guardian
import com.sofia.mobile.domain.model.guardian.toState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

data class Patient(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val gender: Gender,
    val ethnicity: Ethnicity,
    val familyCases: Boolean,
    val pregnancyComplications: Boolean,
    val premature: Boolean,
    val guardians: HashMap<String, Guardian>,
    val registerDate: String,

    var isSelected: Boolean = false
){
    fun addGuardian(guardianDTO: Guardian){
        this.guardians[guardianDTO.id!!] = guardianDTO
    }

    fun getAgeMonths(): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val birthDateLocalDate = LocalDate.parse(this.birthDate, formatter)
        val currentDate = LocalDate.now()
        return ChronoUnit.MONTHS.between(birthDateLocalDate, currentDate)
    }
}

fun Patient.toState(): PatientState{
    val patientState = PatientState()
    patientState.updateId(this.id ?: "")
    patientState.updateFirstName(this.firstName)
    patientState.updateLastName(this.lastName)
    patientState.updateBirthDate(this.birthDate)
    patientState.updateGender(this.gender)
    patientState.updateEthnicity(this.ethnicity)
    patientState.updateFamilyCases(this.familyCases)
    patientState.updatePregnancyComplications(this.pregnancyComplications)
    patientState.updatePremature(this.premature)
    patientState.updateGuardians(this.guardians.values.map { it.toState() })

    return patientState
}