package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CheckListResultScreen(navController: NavController, accuracy: String, result: String){
    Column {
        Text(text = "Acuracia: ${accuracy}%")
        Text(text = "Resultado: $result")
    }
}