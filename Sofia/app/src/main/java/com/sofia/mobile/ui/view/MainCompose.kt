package com.sofia.mobile.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.ui.navigation.introGraph
import com.sofia.mobile.ui.navigation.mainGraph
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.view.contents.appdrawer.AppDrawerContent
import com.sofia.mobile.ui.view.contents.appdrawer.DrawerParams
import com.sofia.mobile.ui.view.contents.containers.LocalizedContent
import com.sofia.mobile.ui.view.screens.intro.SplashScreen
import com.sofia.mobile.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose(
    rd: RelativeDimensions,
    navHostController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    ),
    loginViewModel: LoginViewModel
){
    var isLoggedIn = loginViewModel.isLoggedIn()
        .collectAsState(initial = null)

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
                            defaultPick = MainNavOptions.HomeScreen
                        ){ onUserPickedOption ->
                            navigateToScreen(navHostController, onUserPickedOption)
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
                        mainGraph(navHostController, drawerState, rd)
                    }
                }
            }
        }
    }
}

private fun navigateToScreen(navHostController: NavHostController, screen: MainNavOptions){
    navHostController.navigate(screen.name){
        popUpTo(NavRoutes.MainRoute.name)
    }
}