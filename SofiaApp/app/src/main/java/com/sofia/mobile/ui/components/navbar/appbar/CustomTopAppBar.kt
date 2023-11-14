package com.sofia.mobile.ui.components.navbar.appbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Lilas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
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
           Row(
               modifier = Modifier.height(59.dp),
               verticalAlignment = Alignment.CenterVertically
           ){
               IconButton(onClick = { /* doSomething() */ }) {
                   Icon(
                       imageVector = Icons.Filled.Menu,
                       contentDescription = "Menu",
                       tint = BrillantPurple
                   )
               }
           }
        },
        actions = {
            Row(
                modifier = Modifier.height(59.dp),
                verticalAlignment = Alignment.CenterVertically
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
@Preview
fun CustomTopAppBarPreview() {
    CustomTopAppBar()
}