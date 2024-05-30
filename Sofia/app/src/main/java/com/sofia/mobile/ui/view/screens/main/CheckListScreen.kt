package com.sofia.mobile.ui.view.screens.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme
import com.sofia.mobile.ui.view.components.cards.PatientList
import com.sofia.mobile.ui.view.components.textstyles.ClickableLinkText
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles
import com.sofia.mobile.ui.viewmodel.PatientListViewModel

@Composable
fun CheckListScreen(navController: NavController){

    BackHandler {
        navController.navigate(MainNavOptions.HomeScreen.name)
    }

    Spacer(modifier = Modifier.height(12.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = stringResource(id = R.string.checklist_title),
            style = SofiaTextStyles.h3.copy(color = SofiaColorScheme.BrillantPurple)
        )
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


    Text(
        text = stringResource(id = R.string.list_subtitle, patients.size),
        style = SofiaTextStyles.text1.copy(color = SofiaColorScheme.Gray1)
    )

    if(patients.isEmpty()){
        ClickableLinkText(
            text = stringResource(id = R.string.btn_new_patient),
            onClick =  { navController.navigate(MainNavOptions.PatientRegisterScreen.name) }
        )
    }
    PatientList(
        navController = navController,
        patients = patients,
        navRoute = MainNavOptions.QChatScreen
    )

}