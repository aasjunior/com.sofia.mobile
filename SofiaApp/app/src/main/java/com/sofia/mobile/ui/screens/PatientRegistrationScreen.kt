package com.sofia.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.repository.RepositoryProvider
import com.sofia.mobile.ui.components.forms.PatientForm
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar
import com.sofia.mobile.ui.viewmodels.GenericViewModelFactory
import com.sofia.mobile.ui.viewmodels.PatientViewModel

@Composable
fun PatientRegistrationScreen(navController: NavController){
    val pvm: PatientViewModel = viewModel(
        factory = GenericViewModelFactory { PatientViewModel(RepositoryProvider.pacienteRepository) }
    )
    Scaffold(
        topBar = {
            CustomTopAppBar()
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Spacer(modifier = Modifier.height(12.dp))
            PatientForm(navController, pvm)
        }
    }
}

@Preview
@Composable
private fun PatientRegistrationScreenPreview(){
    PatientRegistrationScreen(navController = rememberNavController())
}