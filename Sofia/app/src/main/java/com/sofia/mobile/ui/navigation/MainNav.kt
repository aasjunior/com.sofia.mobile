package com.sofia.mobile.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.view.contents.containers.BaseScreen
import com.sofia.mobile.ui.view.screens.main.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainGraph(
    navHostController: NavHostController,
    drawerState: DrawerState
){
    navigation(
        startDestination = MainNavOptions.HomeScreen.name,
        route = NavRoutes.MainRoute.name
    ){
        composable(MainNavOptions.HomeScreen.name){
            BaseScreen(navController = navHostController, drawerState = drawerState) {
                HomeScreen(navController = navHostController)
            }
        }
    }
}