package com.sofia.mobile.ui.view.components.form.inputs.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.sofia.mobile.R.string.invalid_confirm_password
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.legend1

@Composable
fun PasswordTextField(
    password: String,
    onValueChange: (String) -> Unit,
    roundedCornerShape: Boolean = true
){
    CustomTextField(
        label = "Password",
        value = password,
        onValueChange = onValueChange,
        isPassword = true,
        roundedCornerShape = roundedCornerShape
    )
}

@Composable
fun ConfirmPassword(){
    var password by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    var isPasswordConfirmed by remember {
        mutableStateOf(true)
    }

    Column {
        PasswordTextField(
            password = password,
            onValueChange = { newPassword ->
                password = newPassword
                if(confirmPassword.isNotEmpty())
                    isPasswordConfirmed = password == confirmPassword
            }
        )

        PasswordTextField(
            password = confirmPassword,
            onValueChange = { newPassword ->
                confirmPassword = newPassword
                isPasswordConfirmed = password == confirmPassword
            }
        )

        if(!isPasswordConfirmed && confirmPassword.isNotEmpty())
            Text(
                stringResource(id = invalid_confirm_password),
                style = legend1
            )
    }
}