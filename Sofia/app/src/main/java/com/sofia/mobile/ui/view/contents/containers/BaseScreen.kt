package com.sofia.mobile.ui.view.contents.containers

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    navController: NavController,
    drawerState: DrawerState,
    content: @Composable () -> Unit
){
    content()
}