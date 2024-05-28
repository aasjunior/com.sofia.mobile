package com.sofia.mobile.ui.view.contents.appdrawer

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import com.sofia.mobile.ui.view.screens.main.ButtonLogout
import kotlinx.coroutines.launch

@Composable
fun <T: Enum<T>> AppDrawerContent(
    drawerState: DrawerState,
    menuItems: List<AppDrawerItemInfo<T>>,
    defaultPick: T,
    navHostController: NavHostController,
    routeMain: MainNavOptions? = null,
    onClick: (String) -> Unit
){
    var currentPick by remember { mutableStateOf(defaultPick) }
    val coroutineScope= rememberCoroutineScope()

    ModalDrawerSheet(drawerContainerColor = Lilas){
        Surface(
            color = Lilas
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    items(menuItems) { item ->
                        AppDrawerItem(
                            item = item,
                            navController = navHostController,
                            routeMain = routeMain
                        ){ navOption ->
                            if(currentPick == navOption){
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                return@AppDrawerItem
                            }

                            currentPick = navOption
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            onClick(navOption.name)
                        }
                    }
                    item {
                        ButtonLogout(
                            navController = navHostController,
                            drawerState = drawerState
                        )
                    }
                }
            }
        }
    }
}