package com.sofia.mobile.ui.view.contents.appbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        modifier = Modifier
            .height(59.dp),
        title = {
            Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically){
                SearchableTopBar(
                    isShowSearchField = true,
                    currentSearchText = "Digite aqui",
                    onSearchTextChanged = {},
                    onSearchDeactivated = { /*TODO*/ },
                    onSearchDispatched = {}
                ){}
            }
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
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "SMS Navigation",
                    tint = BrillantPurple
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Lilas
        )
    )
}
@Composable
private fun AppBarAction(appBarAction: AppBarAction){
    IconButton(onClick = appBarAction.onClick) {
        Icon(
            painter = painterResource(id = appBarAction.icon),
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(id = appBarAction.description)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerIcon(drawerState: DrawerState){
    val coroutineScope = rememberCoroutineScope()
    IconButton(onClick = {
        coroutineScope.launch {
            drawerState.open()
        }
    }) {
        Icon(
            Icons.Rounded.Menu,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(id = R.string.drawer_menu_description)
        )
    }
}