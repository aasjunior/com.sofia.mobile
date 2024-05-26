package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.view.components.cards.PatientList
import com.sofia.mobile.ui.viewmodel.PatientListViewModel

@Composable
fun CheckListScreen(navController: NavController){
    Spacer(modifier = Modifier.height(12.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        SelectPatient(navController)
    }
}

@Composable
private fun SelectPatient(navController: NavController){
    val plvm: PatientListViewModel = viewModel()
    val patients = plvm.patients.sortedBy { it.firstName.lowercase() }

    LaunchedEffect(Unit) {
        plvm.fetchPatients()
    }

    PatientList(
        navController = navController,
        patients = patients,
        navRoute = MainNavOptions.QChatScreen
    )
}