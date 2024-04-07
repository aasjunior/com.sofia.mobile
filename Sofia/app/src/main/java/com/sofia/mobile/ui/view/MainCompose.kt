package com.sofia.mobile.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.ui.navigation.introGraph
import com.sofia.mobile.ui.navigation.mainGraph
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.view.contents.appdrawer.AppDrawerContent
import com.sofia.mobile.ui.view.contents.appdrawer.DrawerParams
import com.sofia.mobile.ui.view.contents.containers.LocalizedContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose(
    navHostController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
){
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
                    NavHost(
                        navController = navHostController,
                        startDestination = NavRoutes.IntroRoute.name
                    ){
                        introGraph(navHostController)
                        mainGraph(navHostController, drawerState)
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