package com.sofia.mobile.ui.view.screens.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.aasjunior.mediapickersuite.domain.model.login.LoginState
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.view.components.form.inputs.textfields.EmailTextField
import com.sofia.mobile.ui.view.components.form.inputs.textfields.PasswordTextField
import com.sofia.mobile.ui.view.contents.containers.IntroContent
import com.sofia.mobile.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel
){
    val loginState by loginViewModel.loginState.collectAsState()

    LaunchedEffect(loginState){
        if(loginState is LoginState.Success){
            navController.navigate(NavRoutes.MainRoute.name){
                popUpTo(NavRoutes.IntroRoute.name){
                    inclusive = true
                }
            }
        }
    }

    IntroContent(navController = navController) {
        FormLogin(onClick = loginViewModel::login)
        when(val state = loginState){
            is LoginState.Loading -> { Text(text = "Validando") }
            is LoginState.Success -> { Text(text = "Logado") }
            is LoginState.Error -> { Text(text = "Erro: ${state.error}") }
            else -> Text(text = "")
        }
    }
}

@Composable
private fun FormLogin(onClick: (String, String) -> Unit){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailTextField(
            email = email,
            onValueChange = { newEmail -> email = newEmail },
            roundedCornerShape = true
        )
        PasswordTextField(
            password = password,
            onValueChange = { newPassword -> password = newPassword }
        )
        Button(
            onClick = {
                onClick(email, password)
            }
        ) {
            Text(text = "Login")
        }
    }
}