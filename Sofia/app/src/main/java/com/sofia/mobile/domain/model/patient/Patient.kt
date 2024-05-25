package com.sofia.mobile.domain.model.patient

import com.sofia.mobile.domain.common.enums.Ethnicity
import com.sofia.mobile.domain.common.enums.Gender
import com.sofia.mobile.domain.model.guardian.Guardian
import java.time.LocalDateTime

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
}
