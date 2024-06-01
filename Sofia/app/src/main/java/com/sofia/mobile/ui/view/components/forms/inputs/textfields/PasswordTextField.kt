package com.sofia.mobile.ui.view.components.forms.inputs.textfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.R.string.invalid_confirm_password
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.legend1
import com.sofia.mobile.ui.view.contents.Dimensions

@Composable
fun PasswordTextField(
    password: String,
    onValueChange: (String?) -> Unit,
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
    onValueChange: (String?) -> Unit,
    width: Dp = Dimensions.formFild264
){
    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    val isPasswordConfirmed by derivedStateOf {
        password == confirmPassword
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)){
        PasswordTextField(
            password = password,
            onValueChange = onValueChange,
            width = width
        )
        PasswordTextField(
            password = confirmPassword,
            onValueChange = {
                confirmPassword = it!!
            },
            isConfirmedPassword = true,
            width = width
        )

        if(!isPasswordConfirmed && confirmPassword.isNotEmpty()){
            Row(
                modifier = Modifier.padding(start = 30.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    stringResource(id = invalid_confirm_password),
                    style = legend1
                )
            }
        }
    }
}