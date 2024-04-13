package com.sofia.mobile.ui.view.screens.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.sofia.mobile.ui.view.components.form.inputs.textfields.EmailTextField
import com.sofia.mobile.ui.view.components.form.inputs.textfields.PasswordTextField

@Composable
fun LoginScreen(
    navController: NavController
){
    Text(text = "Tela de login")
}
/*
@Composable
private fun FormLogin(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailTextField(email = , onValueChange = )
        PasswordTextField(password = , onValueChange = )
        Row {
            Text(text = )
            TextButton(onClick = { /*TODO*/ }) {

            }
        }
        Button(onClick = { /*TODO*/ }) {
        }
    }
}*/