package com.sofia.mobile.ui.view.components.form.inputs.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.sofia.mobile.R
import com.sofia.mobile.R.string.invalid_confirm_password
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.legend1
import com.sofia.mobile.ui.view.contents.Dimensions

@Composable
fun PasswordTextField(
    password: String,
    onValueChange: (String) -> Unit,
    roundedCornerShape: Boolean = true,
    isConfirmedPassword: Boolean = false,
    width: Dp = Dimensions.formFild264
){
    val fieldLabel =
        if(isConfirmedPassword) R.string.form_confirm_password
        else R.string.form_password

    CustomTextField(
        label = stringResource(id = fieldLabel),
        value = password,
        onValueChange = onValueChange,
        isPassword = true,
        roundedCornerShape = roundedCornerShape,
        width = width
    )
}

@Composable
fun ConfirmPassword(
    password: String,
    onValueChange: (String) -> Unit,
    width: Dp = Dimensions.formFild264
){
    val confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    val isPasswordConfirmed by remember {
        mutableStateOf(true)
    }

    Column {
        PasswordTextField(
            password = password,
            onValueChange = onValueChange,
            width = width
        )

        PasswordTextField(
            password = confirmPassword,
            onValueChange = onValueChange,
            isConfirmedPassword = true,
            width = width
        )

        if(!isPasswordConfirmed && confirmPassword.isNotEmpty())
            Text(
                stringResource(id = invalid_confirm_password),
                style = legend1
            )
    }
}