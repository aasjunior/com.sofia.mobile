package com.sofia.mobile.ui.view.screens.intro

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController
import com.sofia.mobile.domain.model.login.LoginState
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.IntroNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.EmailTextField
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.PasswordTextField
import com.sofia.mobile.ui.view.components.textstyles.ClickableLinkText
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles
import com.sofia.mobile.ui.view.contents.Dimensions
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.view.contents.containers.IntroContent
import com.sofia.mobile.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
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

    IntroContent(navController = navController) {
        FormLogin(
            onClick = loginViewModel::login,
            navController = navController,
            relativeDimensions = relativeDimensions,
            loginViewModel = loginViewModel
        )
        when(val state = loginState){
            is LoginState.Loading -> { Text(text = "Validando") }
            is LoginState.Success -> { Text(text = "Logado") }
            is LoginState.Error -> { Text(text = "Erro: ${state.error}") }
            else -> Text(text = "")
        }
    }
}

@Composable
private fun FormLogin(
    onClick: (String, String) -> Unit,
    navController: NavController,
    relativeDimensions: RelativeDimensions,
    loginViewModel: LoginViewModel
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
        PasswordTextField(
            password = password,
            onValueChange = { newPassword -> password = newPassword!! },
            width = relativeDimensions.formFieldIntro
        )
        Redirect {
            navController.navigate(IntroNavOptions.RegisterScreen.name)
        }
        Button(
            modifier = Modifier.width(relativeDimensions.btnWidth),
            onClick = {
                loginViewModel.login(email, password)
            }
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
private fun Redirect(
    redirect: () -> Unit
){
    Spacer(modifier = Modifier.height(Dimensions.spacerValue))
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = Resources.recoverPasswordText) + " ",
            style = SofiaTextStyles.text1
        )
        ClickableLinkText(
            text = stringResource(id = Resources.recoverPasswordLink)
        ) {
            redirect()
        }
    }
    Spacer(modifier = Modifier.height(Dimensions.spacerValue))
}

private object Resources{
    @StringRes
    val recoverPasswordText = R.string.login_forgive
    @StringRes
    val recoverPasswordLink = R.string.login_forgive_link
}

@Preview
@Composable
private fun LoginScreenPreview() {
    SofiaTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            //LoginScreen(navController = rememberNavController(), loginViewModel = viewModel())
        }
    }
}