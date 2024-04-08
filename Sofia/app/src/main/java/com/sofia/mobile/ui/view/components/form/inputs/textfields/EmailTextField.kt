package com.sofia.mobile.ui.view.components.form.inputs.textfields

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sofia.mobile.ui.viewmodel.validation.EmailState

@Composable
fun EmailTextField(
    email: String,
    onValueChange: (String) -> Unit,
    roundedCornerShape: Boolean = false
){
    val emailState = remember { EmailState() }
    emailState.setEmail(email)

    CustomTextField(
        label = "Email",
        value = emailState.email,
        onValueChange = { newEmail ->
            emailState.setEmail(newEmail)
            onValueChange(newEmail)
        },
        roundedCornerShape = roundedCornerShape,
        isError = emailState.hasTriedToEnterEmail && emailState.error != null,
        errorText = emailState.error
    )
}