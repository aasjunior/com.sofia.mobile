package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun CheckListResultScreen(navController: NavController, accuracy: String, result: Int){
    Column {
        Text(text = "Acuracia: $accuracy %")
        Text(text = "Resultado: " + stringResource(id = result))
    }
}