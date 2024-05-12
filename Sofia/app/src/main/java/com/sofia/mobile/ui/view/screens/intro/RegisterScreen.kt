package com.sofia.mobile.ui.view.screens.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aasjunior.mediapickersuite.domain.model.login.LoginState
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.view.components.form.inputs.textfields.ConfirmPassword
import com.sofia.mobile.ui.view.components.form.inputs.textfields.EmailTextField
import com.sofia.mobile.ui.view.contents.Dimensions
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.view.contents.containers.IntroContent
import com.sofia.mobile.ui.viewmodel.LoginViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    relativeDimensions: RelativeDimensions
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

    IntroContent(navController = navController, isRegister = true) {
        FormRegister(onClick = loginViewModel::login, relativeDimensions)
        when(val state = loginState){
            is LoginState.Loading -> { Text(text = "Validando") }
            is LoginState.Success -> { Text(text = "Logado") }
            is LoginState.Error -> { Text(text = "Erro: ${state.error}") }
            else -> Text(text = "")
        }
    }
}

@Composable
private fun FormRegister(
    onClick: (String, String) -> Unit,
    relativeDimensions: RelativeDimensions
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailTextField(
            email = email,
            onValueChange = { newEmail -> email = newEmail },
            roundedCornerShape = true,
            width = relativeDimensions.formFieldIntro
        )
        ConfirmPassword(
            password = password,
            onValueChange = { newPassword -> password = newPassword },
            width = relativeDimensions.formFieldIntro
        )
        Button(
            onClick = {
                onClick(email, password)
            },
            modifier = Modifier.width(relativeDimensions.btnWidth)
        ) {
            Text(text = stringResource(id = R.string.btn_register))
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    SofiaTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            //RegisterScreen(navController = rememberNavController(), loginViewModel = viewModel())
        }
    }
}