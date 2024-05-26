package com.sofia.mobile.domain.model.person

import com.sofia.mobile.domain.common.interfaces.Identifiable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class PersonState(
    protected val _id: MutableStateFlow<String?> = MutableStateFlow(null),
    protected val _firstName: MutableStateFlow<String> = MutableStateFlow(""),
    protected val _lastName: MutableStateFlow<String> = MutableStateFlow(""),
): Identifiable {
    val id: StateFlow<String?> by ::_id
    val firstName: StateFlow<String> by ::_firstName
    val lastName: StateFlow<String> by ::_lastName

    fun updateId(newId: String){
        this._id.value = newId
    }

    fun updateFirstName(newFirstName: String){
        this._firstName.value = newFirstName
    }

    fun updateLastName(newLastName: String){
        this._lastName.value = newLastName
    }
}