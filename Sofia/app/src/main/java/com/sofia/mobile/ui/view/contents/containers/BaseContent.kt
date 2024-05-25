package com.sofia.mobile.ui.view.contents.containers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sofia.mobile.ui.view.contents.appbar.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseContent(
    navController: NavController,
    drawerState: DrawerState,
    bottomBarContent: @Composable (() -> Unit)? = null,
    snackbarHost: @Composable ((SnackbarHostState) -> Unit)? = null,
    content: @Composable () -> Unit
){
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { AppBar(drawerState) },
        bottomBar = { bottomBarContent?.invoke() },
        snackbarHost = { snackbarHost?.invoke(snackbarHostState) ?: DefaultSnackbarHost(snackbarHostState) }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            content()
        }
    }
}

@Composable
private fun DefaultSnackbarHost(snackbarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackbarHostState)
}