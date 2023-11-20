package com.sofia.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.repository.RepositoryProvider
import com.sofia.mobile.ui.components.forms.PatientForm
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar
import com.sofia.mobile.ui.theme.BackgroundSoftLilas
import com.sofia.mobile.ui.viewmodels.GenericViewModelFactory
import com.sofia.mobile.ui.viewmodels.PatientProfileViewModel
import com.sofia.mobile.ui.viewmodels.PatientViewModel

@Composable
fun PatientEditScreen(navController: NavController, pacienteId: Long){
    val ppvm: PatientProfileViewModel = viewModel(
        factory = GenericViewModelFactory {
            PatientProfileViewModel(RepositoryProvider.pacienteRepository)
        }
    )
    LaunchedEffect(pacienteId) {
        ppvm.fetchPatient(pacienteId)
    }

    val paciente = ppvm.patient.value

    val pvm: PatientViewModel = viewModel(
        factory = GenericViewModelFactory { PatientViewModel(RepositoryProvider.pacienteRepository) }
    )
    if(paciente != null){
        pvm.fillFromPatient(paciente)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar()
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(BackgroundSoftLilas)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Spacer(modifier = Modifier.height(12.dp))
            PatientForm(navController, pvm, true)
        }
    }
}