package com.sofia.mobile.ui.view.components.forms.inputs.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.legend1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
import com.sofia.mobile.ui.view.contents.Dimensions

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String? = null,
    value: String,
    width: Dp = Dimensions.formFild264,
    onValueChange: (String?) -> Unit = {},
    singleLine: Boolean = true,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = text1,
    roundedCornerShape: Boolean = false,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isDropDownMenu: Boolean = false,
    trailingIcon: @Composable () -> Unit = {}
){
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    Column {
        OutlinedTextField(
            modifier = modifier.width(width),
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label,
                    style = SofiaTextStyles.label
                )
            },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            placeholder = {
                if(placeholder.isNullOrEmpty()) Text(label)
                else Text(placeholder)
            },
            singleLine = singleLine,
            shape =
                if (roundedCornerShape)
                    RoundedCornerShape(percent = 50)
                else
                    RoundedCornerShape(Dimensions.roundedCorner12),
            visualTransformation =
                if(isPassword && !passwordVisibility)
                    PasswordVisualTransformation()
                else visualTransformation,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BrillantPurple,
                unfocusedBorderColor = BrillantPurple,
                unfocusedTextColor = BrillantPurple,
                focusedLabelColor = BrillantPurple
            ),
            trailingIcon = {
                if(isPassword){
                    IconButton(
                        onClick = {
                            passwordVisibility = !passwordVisibility
                        }
                    ) {
                        Icon(
                            painter =
                            if(passwordVisibility)
                                painterResource(id = R.drawable.ic_eye)
                            else painterResource(id = R.drawable.ic_eye_off),
                            contentDescription = "Toggle password visibility",
                            tint = BrillantPurple
                        )
                    }
                }
                if(isDropDownMenu){
                    trailingIcon()
                }
            }
        )

        if (isError){
            Text(
                text = errorText ?: "",
                style = legend1
            )
        }
    }
}