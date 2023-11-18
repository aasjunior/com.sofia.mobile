package com.sofia.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.repository.RepositoryProvider
import com.sofia.mobile.ui.components.buttons.CustomButton
import com.sofia.mobile.ui.components.buttons.FloatingAddButton
import com.sofia.mobile.ui.components.cards.CustomOptionsCard
import com.sofia.mobile.ui.components.cards.PatientCheckList
import com.sofia.mobile.ui.components.cards.PatientList
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar
import com.sofia.mobile.ui.components.popup.ConfirmAlertDialog
import com.sofia.mobile.ui.components.text.UnderlinedTextNavigation
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Gray1
import com.sofia.mobile.ui.theme.SoftPurple
import com.sofia.mobile.ui.viewmodels.GenericViewModelFactory
import com.sofia.mobile.ui.viewmodels.PatientListViewModel

@Composable
fun PatientListScreen(
    navController: NavController
){
    val viewModel: PatientListViewModel = viewModel(
        factory = GenericViewModelFactory { PatientListViewModel(RepositoryProvider.pacienteRepository) }
    )
    val showDialog = remember { mutableStateOf(false) }
    val isCardOpen = remember { mutableStateOf(false) }
    val isDeleteMode = remember { mutableStateOf(false) }

    // Iniciar a busca de pacientes quando o composable for criado
    LaunchedEffect(Unit) {
        viewModel.fetchPatients()
    }

    // Observar a lista de pacientes e mostrar na tela
    val patients = viewModel.patients.value.sortedBy { it.nome.lowercase() }

    // Observar a mensagens de sucesso e erro e mostrar na tela
    val snackbarHostState = remember { SnackbarHostState() }
    val successMessage = viewModel.successMessage.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(successMessage, errorMessage) {
        when {
            !successMessage.isNullOrEmpty() -> {
                snackbarHostState.showSnackbar(
                    message = successMessage,
                    actionLabel = "Fechar"
                )
                viewModel.successMessage.value = null
            }
            !errorMessage.isNullOrEmpty() -> {
                snackbarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = "Fechar"
                )
                viewModel.errorMessage.value = null
            }
        }
    }

    val options = mutableListOf<Pair<String, () -> Unit>>()
    options.add("Novo" to { navController.navigate("patientRegistration") })
    if(patients.isNotEmpty()){
        if(isDeleteMode.value){
            options.add("Cancelar" to {
                isDeleteMode.value = !isDeleteMode.value
                if(!isDeleteMode.value){
                    viewModel.deselectAllPatients()
                }
            })
        }else{
            options.add("Deletar" to {
                isDeleteMode.value = !isDeleteMode.value
                if(!isDeleteMode.value){
                    viewModel.deselectAllPatients()
                }
            })
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar()
        },
        bottomBar = {
            if(isDeleteMode.value){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    CustomButton(
                        text = "Deletar",
                        onClick = {
                            if(viewModel.totalChecked > 0)
                                showDialog.value = true
                        }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ){
                if(isDeleteMode.value){
                    Text(
                        text = "${viewModel.totalChecked} selecionado(s)",
                        style = h3.copy(color = BrillantPurple)
                    )
                }else{
                    Text(
                        text = stringResource(id = R.string.patient_list_header),
                        style = h3.copy(color = BrillantPurple)
                    )

                    Text(
                        text = "${patients.size} pacientes cadastrados",
                        style = body1.copy(color = Gray1)
                    )
                }
            }

            if(patients.isEmpty()){
                isDeleteMode.value = false
                UnderlinedTextNavigation(
                    text = "Cadastrar novo paciente",
                    onClick =  { navController.navigate("patientRegistration") }
                )
            }else {
                Row(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isCardOpen.value) {
                        CustomOptionsCard(options)
                    } else {
                        FloatingAddButton(
                            onClick = { navController.navigate("patientRegistration") }
                        )
                    }

                    IconButton(onClick = { isCardOpen.value = !isCardOpen.value }) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = null,
                            tint = SoftPurple
                        )
                    }
                }
            }

            if(isDeleteMode.value) {
                PatientCheckList(viewModel)
            }else{
                PatientList(patients)
            }
            if(showDialog.value){
                ConfirmAlertDialog(
                    title = "Confirmação de exclusão",
                    text = "Tem certeza de que deseja excluir os pacientes selecionados?",
                    onConfirm = {
                        viewModel.deleteSelectedPatients()
                        showDialog.value = false
                    },
                    onDismiss = {
                        showDialog.value = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PatientListScreenPreview(){
    PatientListScreen(navController = rememberNavController())
}