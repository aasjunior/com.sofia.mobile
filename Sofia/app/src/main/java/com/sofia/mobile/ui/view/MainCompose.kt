package com.sofia.mobile.ui.view

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.config.Injector
import com.sofia.mobile.config.retrofit.ApiClient
import com.sofia.mobile.config.security.SecurePreferences
import com.sofia.mobile.domain.model.login.RefreshRequest
import com.sofia.mobile.domain.service.LoginService
import com.sofia.mobile.ui.navigation.introGraph
import com.sofia.mobile.ui.navigation.mainGraph
import com.sofia.mobile.ui.navigation.routes.IntroNavOptions
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.view.components.popup.CustomAlertDialog
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.view.contents.appdrawer.AppDrawerContent
import com.sofia.mobile.ui.view.contents.appdrawer.DrawerParams
import com.sofia.mobile.ui.view.contents.containers.LocalizedContent
import com.sofia.mobile.ui.view.screens.intro.SplashScreen
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel
import com.sofia.mobile.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun MainCompose(
    rd: RelativeDimensions,
    navHostController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    ),
    loginViewModel: LoginViewModel,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit
){
    val isLoggedIn = loginViewModel.isLoggedIn()
        .collectAsState(initial = null)

    val errorMessage by loginViewModel.errorMessage.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

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

                        navigateToLoginScreen(navHostController, securePreferences)
                    }
                }
            } else {
                navigateToLoginScreen(navHostController, securePreferences)
            }
        }catch(e: Exception){
            Log.i("Initialize", "$e")
            loginViewModel.updateErrrorMessage(e.message!!)
        }
    }

    SofiaTheme(darkTheme = false){
        Surface(
            modifier = Modifier.fillMaxSize()
        ){
            LocalizedContent {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        AppDrawerContent(
                            drawerState = drawerState,
                            menuItems = DrawerParams.drawerButtons,
                            defaultPick = MainNavOptions.HomeScreen,
                            navHostController = navHostController,
                            routeMain = MainNavOptions.HomeScreen
                        ){ onUserPickedOption ->
                            navHostController.navigate(onUserPickedOption)
                        }
                    }
                ) {
                    val startDestination: MutableState<String> = mutableStateOf(NavRoutes.IntroRoute.name)

                    when(isLoggedIn.value){
                        null -> SplashScreen(navHostController)

                        true -> startDestination.value = NavRoutes.MainRoute.name

                        false -> startDestination.value = NavRoutes.IntroRoute.name
                    }

                    NavHost(
                        navController = navHostController,
                        startDestination = startDestination.value
                    ){
                        introGraph(navHostController, rd)
                        mainGraph(
                            navHostController,
                            drawerState,
                            rd,
                            imagePickerViewModel,
                            onPickImageClick
                        )
                    }
                }
            }
        }
    }

    if(showDialog) {
        CustomAlertDialog(
            onDismissRequest = { !showDialog },
            text = errorMessage!!
        ) {
            showDialog = false
        }
    }
}

private fun navigateToScreen(navHostController: NavHostController, screen: MainNavOptions){
    navHostController.navigate(screen.name){
        popUpTo(NavRoutes.MainRoute.name)
    }
}

private fun navigateToLoginScreen(navHostController: NavHostController, securePreferences: SecurePreferences){
    runBlocking {
        securePreferences.clearTokens()
        navHostController.navigate(IntroNavOptions.LoginScreen.name){
            popUpTo(NavRoutes.MainRoute.name)
        }
    }
}