package com.sofia.mobile.ui.view.screens.main

import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.viewmodel.LoginViewModel

@Composable
fun HomeScreen(
    navController: NavController,

){
    val lvm: LoginViewModel = viewModel()
    Text(text = "Home")
    Button(
        onClick = {
            lvm.logout()
            navController.navigate(NavRoutes.IntroRoute.name){
                popUpTo(NavRoutes.IntroRoute.name){
                    inclusive = true
                }
            }
        }
    ) {
        Text(text = "logout")
    }
}