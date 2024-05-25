package com.sofia.mobile.ui.view.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PatientEditScreen(
    navController: NavController,
    patientId: String
){
    Text(text = "PatientEdit")
}