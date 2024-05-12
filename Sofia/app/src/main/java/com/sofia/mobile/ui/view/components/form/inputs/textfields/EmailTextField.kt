package com.sofia.mobile.ui.view.components.form.inputs.textfields

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import com.sofia.mobile.ui.view.contents.Dimensions
import com.sofia.mobile.ui.viewmodel.validation.EmailState

@Composable
fun EmailTextField(
    email: String,
    onValueChange: (String) -> Unit,
    roundedCornerShape: Boolean = false,
    width: Dp = Dimensions.formFild264
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
        width = width,
        roundedCornerShape = roundedCornerShape,
        isError = emailState.hasTriedToEnterEmail && emailState.error != null,
        errorText = emailState.error
    )
}