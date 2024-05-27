package com.sofia.mobile.ui.view.contents.appbar

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import com.sofia.mobile.ui.theme.SofiaTypography.text12
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    drawerState: DrawerState? = null,
    navigationIcon: (@Composable () -> Unit)? = null,
    @StringRes title: Int? = null,
    appBarActions: List<AppBarAction>? = null
){
    var text by remember { mutableStateOf("") }

    CenterAlignedTopAppBar(
        modifier = Modifier.height(59.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ){
                SearchableTopBar(
                    isShowSearchField = true,
                    currentSearchText = text,
                    onSearchTextChanged = { text = it },
                    onSearchDeactivated = { text = "" },
                    onSearchDispatched = {}
                ) {
                    
                }
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
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Email,
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
    IconButton(onClick = appBarAction.onClick) {
        Icon(
            painter = painterResource(id = appBarAction.icon),
            modifier = Modifier.size(24.dp),
            tint = BrillantPurple,
            contentDescription = stringResource(id = appBarAction.description)
        )
    }
}

@Composable
private fun DrawerIcon(drawerState: DrawerState){
    val coroutineScope = rememberCoroutineScope()
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
                Icons.Rounded.Menu,
                tint = BrillantPurple,
                contentDescription = stringResource(id = R.string.drawer_menu_description)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchTopBar(){
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .height(50.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {
                active = false
            },
            active = active,
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = null)
            },
            trailingIcon = {
                if(active){
                    Icon(
                        modifier = Modifier.clickable {
                            if(text.isNotEmpty()){
                                text = ""
                            }else{
                                active = false
                            }
                        },
                        imageVector = Icons.Filled.Close,
                        contentDescription = null
                    )
                }
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.form_search),
                    style = text12.copy(Color.Gray, lineHeight = 20.sp)
                )
            },

            onActiveChange = {
                active = it
            }
        ){}
    }
}

@Composable
fun CustomSearchBar() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(56.dp),
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = { text = "" }) {
                    Icon(Icons.Filled.Close, contentDescription = null)
                }
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.form_search),
                color = Color.Gray
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp)
    )
}

@Preview
@Composable
private fun CustomSearchBarPreview(){
    SearchTopBar()
}