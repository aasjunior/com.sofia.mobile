package com.sofia.mobile.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sofia.mobile.ui.navigation.routes.IntroNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.view.screens.intro.LoadingScreen
import com.sofia.mobile.ui.view.screens.intro.LoginScreen
import com.sofia.mobile.ui.view.screens.intro.RegisterScreen
import com.sofia.mobile.ui.view.screens.intro.SplashScreen
import com.sofia.mobile.ui.view.screens.intro.WelcomeScreen
import com.sofia.mobile.ui.viewmodel.LoginViewModel

fun NavGraphBuilder.introGraph(navHostController: NavHostController){
    navigation(
        startDestination = IntroNavOptions.SplashScreen.name,
        route = NavRoutes.IntroRoute.name
    ){
        composable(IntroNavOptions.SplashScreen.name){
            SplashScreen(navController = navHostController)
        }
        composable(IntroNavOptions.LoginScreen.name){
            val loginViewModel: LoginViewModel = viewModel()

            LoginScreen(
                navController = navHostController,
                loginViewModel = loginViewModel
            )
        }
        composable(IntroNavOptions.RegisterScreen.name){
            RegisterScreen(navController = navHostController)
        }
        composable(IntroNavOptions.LoadingScreen.name){
            LoadingScreen(navController = navHostController)
        }
        composable(IntroNavOptions.WelcomeScreen.name){
            WelcomeScreen(navController = navHostController)
        }
    }
}