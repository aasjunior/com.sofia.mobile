package com.sofia.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.sofia.mobile.ui.components.cards.PatientProfileCard
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar
import com.sofia.mobile.ui.viewmodels.GenericViewModelFactory
import com.sofia.mobile.ui.viewmodels.PatientProfileViewModel

@Composable
fun PatientProfileScreen(
    navController: NavController,
    patientId: Long
){
    // Obtenha uma instância do ViewModel
    val viewModel: PatientProfileViewModel = viewModel(
        factory = GenericViewModelFactory { PatientProfileViewModel(RepositoryProvider.pacienteRepository) }
    )

    // Busque os detalhes do paciente quando o componente for montado
    LaunchedEffect(patientId) {
        viewModel.fetchPatient(patientId)
    }

    // Observe o estado do paciente e exiba os detalhes do paciente
    val paciente = viewModel.patient.value
    val errorMessage = viewModel.errorMessage.value

    if(paciente != null){
        Scaffold(
            topBar = {
                CustomTopAppBar()
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                PatientProfileCard(paciente)
            }
        }
    } else if(errorMessage != null){
        Text(text = "deu erro")
    } else {
        Text(text = "carregando...")
    }
}