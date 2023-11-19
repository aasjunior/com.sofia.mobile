package com.sofia.mobile.ui.components.forms

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.repository.RepositoryProvider
import com.sofia.mobile.ui.components.buttons.CustomButton
import com.sofia.mobile.ui.components.buttons.CustomOutlinedButton
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.viewmodels.GenericViewModelFactory
import com.sofia.mobile.ui.viewmodels.PatientViewModel
import kotlinx.coroutines.launch

@Composable
fun PatientForm(navController: NavController){
    var currentStep by remember { mutableStateOf(0) }
    val pvm: PatientViewModel = viewModel(
        factory = GenericViewModelFactory { PatientViewModel(RepositoryProvider.pacienteRepository) }
    )
    val courotineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var showDialogSuccess by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("Preencha todos os campos.") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var textHeader = if(currentStep == 2)
                             R.string.patient_registration_header_02
                         else
                             R.string.patient_registration_header_01

        Text(
            text = stringResource(id = textHeader),
            style = h3.copy(color = BrillantPurple)
        )
        FormProgress(currentStep)
        when(currentStep){
            0 -> FormInfo(
                pvm = pvm
            )
            1 -> FormPerfil(
                pvm = pvm
            )
            2 -> FormResponsavel(
                pvm = pvm
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            when(currentStep){
                0 -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        CustomButton(text = "Próximo", onClick = {
                            if(isFormInfoValid(pvm = pvm)) {
                                currentStep++
                            }else{
                                showDialog = true
                            }
                        })
                    }
                }
                1 -> {
                    CustomOutlinedButton(text = "Voltar", onClick = {
                        currentStep--
                    })
                    CustomButton(text = "Próximo", onClick = {
                        if(isFormPerfilValid(pvm = pvm)) {
                            currentStep++
                        }else{
                            showDialog = true
                        }
                    })
                }
                2 -> {
                    CustomOutlinedButton(text = "Voltar", onClick = {
                        currentStep--
                    })
                    CustomButton(text = "Salvar", onClick = {
                        if(isFormResponsavelValid(pvm = pvm)) {
                            courotineScope.launch {
                                try{
                                    alertMessage = pvm.sendData()
                                    if(alertMessage == "sucesso"){
                                        showDialogSuccess = true
                                    }else{
                                        showDialog = true
                                    }

                                }catch(e: Exception) {
                                    // Registre o erro aqui
                                    Log.e("PatientForm", "Ocorreu um erro ao enviar os dados", e)
                                }
                            }
                        }else{
                            showDialog = true
                        }
                    })
                }
            }
        }
        if(showDialog) {
            AlertDialog(
                onDismissRequest = { !showDialog },
                title = { Text("Mensagem") },
                text = { Text(alertMessage) },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Ok")
                    }
                }
            )
        }

        if(showDialogSuccess){
            AlertDialog(
                onDismissRequest = { !showDialog },
                title = { Text("Mensagem") },
                text = { Text("Dados cadastrados com sucesso.") },
                confirmButton = {
                    Button(onClick = {
                        navController.navigate("patientList")
                        showDialog = false

                    }) {
                        Text("Ok")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun PatientFormPreview(){
    PatientForm(rememberNavController())
}