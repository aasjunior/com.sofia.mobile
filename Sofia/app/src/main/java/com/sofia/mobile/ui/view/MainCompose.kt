package com.sofia.mobile.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
                        null -> SplashScreen(navHostController, loginViewModel)

                        true -> if(loginViewModel.isFirstLogin.value)  navHostController.navigate(IntroNavOptions.GettingStartedScreen.name)
                                else startDestination.value = NavRoutes.MainRoute.name

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