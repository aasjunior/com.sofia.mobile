package com.sofia.mobile.ui.view.screens.intro

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sofia.mobile.R
import com.sofia.mobile.config.Injector
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.config.security.SecurePreferences
import com.sofia.mobile.domain.model.login.RefreshRequest
import com.sofia.mobile.domain.service.LoginService
import com.sofia.mobile.ui.navigation.routes.IntroNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import com.sofia.mobile.ui.view.components.Copyright
import com.sofia.mobile.ui.view.components.Logo
import com.sofia.mobile.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun SplashScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel
){
    val showSecondBox = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit){
        delay(2000)
        showSecondBox.value = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Style.bgColor)
    ){
        if(showSecondBox.value){
            SecondStage()
            LaunchedEffect(key1 = Unit) {
                val securePreferences: SecurePreferences = Injector.provideSecurePreferences()
                val loginService: LoginService = ApiClient.loginService
                val accessToken = securePreferences.accessToken.first()
                try {
                    if(accessToken != null) {
                        val response = loginService.checkTokenValidity(RefreshRequest(accessToken))
                        if (response.isSuccessful && response.body() == false) {
                            val refreshResponse = loginService.refresh(RefreshRequest(accessToken))
                            if (refreshResponse.isSuccessful) {
                                val refreshedToken = refreshResponse.body()?.accessToken
                                securePreferences.clearTokens()
                                securePreferences.saveAccessToken(refreshedToken!!)
                            } else {

                                navigateToLoginScreen(navController, securePreferences)
                            }
                        }
                    } else {
                        navigateToLoginScreen(navController, securePreferences)
                    }
                }catch(e: Exception){
                    Log.i("Initialize", "$e")
                    loginViewModel.updateErrrorMessage(e.message!!)
                }
            }
        }else{
            FirstStage(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = Style.paddingLogo)
            )
        }
        
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Style.paddingBottom)
        ){
            Copyright()
        }
    }
}

@Composable
private fun FirstStage(modifier: Modifier){
    Row(modifier = modifier){
        Logo(modifier = Modifier.size(Style.sizeFirstLogo))
    }
    Image(
        painter = painterResource(id = R.drawable.ic_sorrident_star),
        contentDescription = "Sorrident Star",
        modifier = Modifier.size(Style.sizeSorridentStar)
    )
}

@Composable
private fun SecondStage(){
    Logo(
        modifier = Modifier.size(Style.sizeSecondLogo),
        logo = R.drawable.ic_sorrident_star
    )
}

private object Style{
    val bgColor = Lilas
    val spacerValue = 16.dp
    val paddingLogo = 96.dp
    val sizeFirstLogo = 170.dp
    val sizeSecondLogo = 256.dp
    val sizeSorridentStar = 180.dp
    val paddingBottom = 28.dp
}

private fun navigateToLoginScreen(navHostController: NavHostController, securePreferences: SecurePreferences){
    runBlocking {
        securePreferences.clearTokens()
        navHostController.navigate(IntroNavOptions.LoginScreen.name){
            popUpTo(NavRoutes.MainRoute.name)
        }
    }
}