package com.sofia.mobile.ui.view.components.forms.inputs.textfields

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sofia.mobile.R.string.form_phone
import com.sofia.mobile.ui.viewmodel.visualtransformation.PhoneVisualTransformation

@Composable
fun PhoneTextField(
    phone: String,
    onValueChange: (String) -> Unit
){
    CustomTextField(
        label = stringResource(id = form_phone),
        value = phone,
        onValueChange = {
            if(validPhone(it!!)){
                onValueChange(it)
            }
        },
        visualTransformation = PhoneVisualTransformation()
    )
}

private fun validPhone(phone: String): Boolean{
    val digitsOnly = phone.filter { it.isDigit() }
    return digitsOnly.length <= 11
}
