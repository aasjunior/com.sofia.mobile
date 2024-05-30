package com.sofia.mobile.ui.view.contents.appbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    drawerState: DrawerState? = null,
    navigationIcon: (@Composable () -> Unit)? = null,
    @StringRes title: Int? = null,
    appBarActions: List<AppBarAction>? = null
){
    CenterAlignedTopAppBar(
        modifier = Modifier.height(60.dp),
        title = {
            AppSearchBar()
        },
        navigationIcon = {
            if(drawerState != null && navigationIcon == null)
                DrawerIcon(drawerState)
            else
                navigationIcon?.invoke()
        },
        actions = {
            appBarActions?.let {
                for(appBarAction in it){
                    AppBarAction(appBarAction)
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "SMS Navigation",
                        tint = BrillantPurple
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Lilas
        )
    )
}
@Composable
private fun AppBarAction(appBarAction: AppBarAction){
    IconButton(
        modifier = Modifier.size(24.dp),
        onClick = appBarAction.onClick
    ) {
        Icon(
            painter = painterResource(id = appBarAction.icon),
            modifier = Modifier.fillMaxSize(),
            tint = BrillantPurple,
            contentDescription = stringResource(id = appBarAction.description)
        )
    }
}

@Composable
private fun DrawerIcon(drawerState: DrawerState){
    val coroutineScope = rememberCoroutineScope()
    val menuIcon: Painter = painterResource(id = R.drawable.ic_menu)
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            coroutineScope.launch {
                drawerState.open()
            }
        }) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = menuIcon,
                tint = BrillantPurple,
                contentDescription = stringResource(id = R.string.drawer_menu_description)
            )
        }
    }
}

@Composable
private fun AppSearchBar(){
    var text by remember { mutableStateOf("") }
    var expand by remember { mutableStateOf(false) }

    when(expand){
        false -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ){
                IconButton(onClick = { expand = true }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_searchbar),
                        tint = BrillantPurple,
                        contentDescription = null
                    )
                }
            }
        }
        true -> {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ){
                SearchTopBar(
                    currentSearchText = text,
                    onSearchTextChanged = { text = it },
                    onSearchDeactivated = {
                        if(text.isNotEmpty()) text = ""
                        else expand = false
                    },
                    onSearchDispatched = {}
                )
            }
        }
    }
}