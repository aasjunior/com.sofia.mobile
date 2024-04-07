package com.sofia.mobile.domain.model.patient

import com.sofia.mobile.domain.common.enums.Ethnicity
import com.sofia.mobile.domain.common.enums.Gender
import com.sofia.mobile.domain.model.guardian.GuardianDTO
import java.time.LocalDateTime

data class PatientDTO(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val birthDate: String,
    val ethnicity: Ethnicity,
    val familyCases: Boolean,
    val pregnancyComplications: Boolean,
    val premature: Boolean,
    val guardians: HashMap<String, GuardianDTO>,
    val registerDate: LocalDateTime
){
    fun addGuardian(guardianDTO: GuardianDTO){
        this.guardians[guardianDTO.id!!] = guardianDTO
    }
}
