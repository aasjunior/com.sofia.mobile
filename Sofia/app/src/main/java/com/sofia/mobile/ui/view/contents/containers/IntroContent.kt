package com.sofia.mobile.ui.view.contents.containers

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.IntroNavOptions
import com.sofia.mobile.ui.view.components.Copyright
import com.sofia.mobile.ui.view.components.Logo
import com.sofia.mobile.ui.view.components.textstyles.ClickableLinkText
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
import com.sofia.mobile.ui.view.contents.Dimensions

@Composable
fun IntroContent(
    navController: NavController,
    isRegister: Boolean = false,
    form: @Composable () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = Dimensions.spacerValue),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Header(isRegister)
            Spacer(modifier = Modifier.height(Dimensions.spacerValue))
            form()
            Redirect(isRegister) {
                if(isRegister){
                    navController.navigate(IntroNavOptions.LoginScreen.name)
                }else{
                    navController.navigate(IntroNavOptions.RegisterScreen.name)
                }
            }
        }
        Copyright()
    }
}

@Composable
private fun Header(isRegister: Boolean){
    Spacer(modifier = Modifier.height(Dimensions.spacerValue))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Logo(modifier = Modifier.height(Dimensions.heightLogo))
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
    Spacer(modifier = Modifier.height(Dimensions.spacerValue))
}

@Composable
private fun Redirect(
    isRegister: Boolean,
    redirect: () -> Unit
){
    Spacer(modifier = Modifier.height(Dimensions.spacerValue))
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id =
                if(isRegister) Resources.login
                else Resources.register
            ) + " ",
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
    Spacer(modifier = Modifier.height(Dimensions.spacerValue))
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
    val redirectLogin = R.string.register_logon_link
}