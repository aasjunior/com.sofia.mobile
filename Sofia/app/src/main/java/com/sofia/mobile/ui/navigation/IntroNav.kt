package com.sofia.mobile.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sofia.mobile.ui.navigation.routes.IntroNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.view.screens.intro.GettingStartedScreen
import com.sofia.mobile.ui.view.screens.intro.LoadingScreen
import com.sofia.mobile.ui.view.screens.intro.LoginScreen
import com.sofia.mobile.ui.view.screens.intro.RecoverPasswordScreen
import com.sofia.mobile.ui.view.screens.intro.RegisterScreen
import com.sofia.mobile.ui.view.screens.intro.SplashScreen
import com.sofia.mobile.ui.viewmodel.LoginViewModel

fun NavGraphBuilder.introGraph(navHostController: NavHostController, rd: RelativeDimensions){
    navigation(
        startDestination = IntroNavOptions.SplashScreen.name,
        route = NavRoutes.IntroRoute.name
    ){
        composable(IntroNavOptions.SplashScreen.name){
            val loginViewModel: LoginViewModel = viewModel()

            SplashScreen(navController = navHostController, loginViewModel)
        }
        composable(IntroNavOptions.LoginScreen.name){
            val loginViewModel: LoginViewModel = viewModel()

            LoginScreen(
                navController = navHostController,
                lvm = loginViewModel,
                relativeDimensions = rd
            )
        }
        composable(IntroNavOptions.RegisterScreen.name){
            val loginViewModel: LoginViewModel = viewModel()

            RegisterScreen(
                navController = navHostController,
                loginViewModel = loginViewModel,
                relativeDimensions = rd
            )
        }
        composable(IntroNavOptions.RecoverPasswordScreen.name){
            RecoverPasswordScreen(navHostController)
        }
        composable(IntroNavOptions.LoadingScreen.name){
            LoadingScreen(navController = navHostController)
        }
        composable(IntroNavOptions.GettingStartedScreen.name) {
            GettingStartedScreen(navHostController)
        }
    }
}