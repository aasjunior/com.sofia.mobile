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
import com.sofia.mobile.ui.view.screens.main.CheckListResultScreen
import com.sofia.mobile.ui.view.screens.main.CheckListScreen
import com.sofia.mobile.ui.view.screens.main.HomeScreen
import com.sofia.mobile.ui.view.screens.main.PatientEditScreen
import com.sofia.mobile.ui.view.screens.main.PatientListScreen
import com.sofia.mobile.ui.view.screens.main.PatientProfileScreen
import com.sofia.mobile.ui.view.screens.main.PatientRegisterScreen
import com.sofia.mobile.ui.view.screens.main.QChatScreen
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
            BaseContent(navHostController,drawerState) {
                HomeScreen(navHostController)
            }
        }
        composable(MainNavOptions.PatientListScreen.name){
            PatientListScreen(navHostController, drawerState)
        }
        composable(MainNavOptions.PatientRegisterScreen.name){
            BaseContent(navHostController, drawerState) {
                PatientRegisterScreen(navHostController, imagePickerViewModel, onPickImageClick)
            }
        }
        composable(
            route = "${MainNavOptions.PatientProfileScreen.name}/{patientId}",
            arguments = listOf(navArgument("patientId") {
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val patientId = backStackEntry.arguments?.getString("patientId")
            BaseContent(navHostController, drawerState) {
                PatientProfileScreen(
                    navHostController,
                    patientId ?: "",
                    imagePickerViewModel,
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
            BaseContent(navHostController, drawerState) {
                PatientEditScreen(
                    navHostController, patientId ?: ""
                )
            }
        }
        composable(MainNavOptions.CheckListScreen.name){
            BaseContent(navHostController, drawerState) {
                CheckListScreen(navHostController)
            }
        }
        composable(
            route = "${MainNavOptions.QChatScreen.name}/{patientId}",
            arguments = listOf(navArgument("patientId") {
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val patientId = backStackEntry.arguments?.getString("patientId")
            BaseContent(navHostController, drawerState) {
                QChatScreen(navHostController, patientId ?: "")
            }
        }
        composable(
            route = "${MainNavOptions.CheckListResultScreen.name}/{result}",
            arguments = listOf(
                navArgument("result") { type = NavType.BoolType}
            )
        ){ backStackEntry ->
            val result = backStackEntry.arguments?.getBoolean("result")

            BaseContent(navHostController, drawerState) {
                CheckListResultScreen(navHostController, result!!)
            }
        }
    }
}