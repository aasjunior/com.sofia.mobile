package com.sofia.mobile.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.repository.RepositoryProvider
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
    val patient = viewModel.patient.value
    val errorMessage = viewModel.errorMessage.value

    if(patient != null){
        Text(text = "${patient.nome}")
    } else if(errorMessage != null){
        Text(text = "deu erro")
    } else {
        Text(text = "carregando...")
    }
}