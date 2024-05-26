package com.sofia.mobile.ui.view.components.forms.inputs.textfields

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FormField(
    label: String,
    stateFlow: StateFlow<String>,
    onValueChange: (String?) -> Unit,
    isEmail: Boolean = false,
    isPhone: Boolean = false
){
    val value by stateFlow.collectAsState()

    if(isEmail){
        EmailTextField(
            email = value,
            onValueChange = onValueChange
        )
    }else if(isPhone){
        PhoneTextField(
            phone = value,
            onValueChange = onValueChange
        )
    }else{
        CustomTextField(
            label = label,
            value = value,
            onValueChange = onValueChange
        )
    }
}