package com.sofia.mobile.ui.view.contents.containers

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.IntroNavOptions
import com.sofia.mobile.ui.view.components.Copyright
import com.sofia.mobile.ui.view.components.Logo
import com.sofia.mobile.ui.view.components.textstyles.ClickableLinkText
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1

@Composable
fun IntroContent(
    navController: NavController,
    isRegister: Boolean = false,
    form: @Composable () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Header(isRegister)
        form()
        Redirect(isRegister) {
            if(isRegister){
                navController.navigate(IntroNavOptions.LoginScreen.name)
            }else{
                navController.navigate(IntroNavOptions.RegisterScreen.name)
            }
        }
    }
}

@Preview
@Composable
private fun IntroContentPreview(){
    IntroContent(navController = rememberNavController()){

    }
}

@Composable
private fun Header(isRegister: Boolean){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Logo(modifier = Modifier.height(Style.heightLogo))
        Text(
            text = stringResource(id = Resources.welcome),
            style = h1
        )
        Text(
            text = stringResource(id =
                if(isRegister) Resources.headerRegister
                else Resources.headerLogin
            ),
            style = text1
        )
    }
}

@Composable
private fun Redirect(
    isRegister: Boolean,
    redirect: () -> Unit
){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Row {
            Text(
                text = stringResource(id =
                    if(isRegister) Resources.login
                    else Resources.register
                ),
                style = text1
            )
            ClickableLinkText(
                text = stringResource(id =
                    if(isRegister) Resources.redirectLogin
                    else Resources.redirectRegister
                )
            ) {
                redirect()
            }
        }
        Copyright()
    }
}

private object Style{
    val spacerValue = 16.dp
    val paddingLogo = 96.dp
    val heightLogo = 24.dp
    val paddingBottom = 28.dp
}

private object Resources{
    @StringRes
    val welcome = R.string.login_welcome
    @StringRes
    val headerLogin = R.string.login_header
    @StringRes
    val headerRegister = R.string.register_header
    @StringRes
    val register = R.string.login_register
    @StringRes
    val redirectRegister = R.string.login_register_link
    @StringRes
    val login = R.string.register_login
    @StringRes
    val redirectLogin = R.string.login_register_link
}