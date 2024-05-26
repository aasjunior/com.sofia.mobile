package com.sofia.mobile.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.view.contents.containers.BaseContent
import com.sofia.mobile.ui.view.screens.main.HomeScreen
import com.sofia.mobile.ui.view.screens.main.PatientEditScreen
import com.sofia.mobile.ui.view.screens.main.PatientListScreen
import com.sofia.mobile.ui.view.screens.main.PatientRegisterScreen
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel

fun NavGraphBuilder.mainGraph(
    navHostController: NavHostController,
    drawerState: DrawerState,
    rd: RelativeDimensions,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit
){
    navigation(
        startDestination = MainNavOptions.HomeScreen.name,
        route = NavRoutes.MainRoute.name
    ){
        composable(MainNavOptions.HomeScreen.name){
            BaseContent(navController = navHostController, drawerState = drawerState) {
                HomeScreen(navController = navHostController)
            }
        }
        composable(MainNavOptions.PatientListScreen.name){
            PatientListScreen(navController = navHostController, drawerState = drawerState)
        }
        composable(MainNavOptions.PatientRegisterScreen.name){
            BaseContent(navController = navHostController, drawerState = drawerState) {
                PatientRegisterScreen(
                    navController = navHostController,
                    imagePickerViewModel = imagePickerViewModel,
                    onPickImageClick
                )
            }
        }
        composable(
            route = "${MainNavOptions.PatientEditScreen.name}/{patientId}",
            arguments = listOf(navArgument("patientId") {
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val patientId = backStackEntry.arguments?.getString("patientId")
            PatientEditScreen(navController = navHostController, patientId = patientId ?: "")
        }
    }
}