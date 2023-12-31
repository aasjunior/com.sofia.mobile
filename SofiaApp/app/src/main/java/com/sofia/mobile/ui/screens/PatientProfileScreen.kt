package com.sofia.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.repository.RepositoryProvider
import com.sofia.mobile.ui.components.cards.PatientProfileCard
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BackgroundSoftLilas
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.viewmodels.GenericViewModelFactory
import com.sofia.mobile.ui.viewmodels.PatientProfileViewModel

@Composable
fun PatientProfileScreen(
    navController: NavController,
    patientId: Long
){
    // Obtem uma instância do ViewModel
    val viewModel: PatientProfileViewModel = viewModel(
        factory = GenericViewModelFactory { PatientProfileViewModel(RepositoryProvider.pacienteRepository) }
    )

    // Busca os detalhes do paciente quando o componente é montado
    LaunchedEffect(patientId) {
        viewModel.fetchPatient(patientId)
    }

    // Observa o estado do paciente e exibe os detalhes do paciente
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
                    .fillMaxSize()
                    .background(BackgroundSoftLilas),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.patient_profile_header),
                    style = h3.copy(color = BrillantPurple)
                )
                PatientProfileCard(
                    paciente = paciente,
                    onClick = { navController.navigate("patientEdit/${paciente.id}") }
                )
            }
        }
    } else if(errorMessage != null){
        Text(text = "deu erro")
    } else {
        Text(text = "carregando...")
    }
}