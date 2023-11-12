package com.sofia.mobile.ui.components.forms

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.components.inputs.ImagePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sofia.mobile.R
import com.sofia.mobile.api.RetrofitInstance
import com.sofia.mobile.data.PacienteRepository
import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.domain.Sexo
import com.sofia.mobile.ui.components.buttons.CustomButton
import com.sofia.mobile.ui.components.inputs.CustomDatePicker
import com.sofia.mobile.ui.components.inputs.CustomSelectBox
import com.sofia.mobile.ui.components.inputs.OutlineRadioButton
import com.sofia.mobile.ui.components.inputs.OutlineTextRadioButton
import com.sofia.mobile.ui.components.text.body2
import com.sofia.mobile.ui.components.text.fs12
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Gray1
import com.sofia.mobile.ui.viewmodels.PatientViewModel
import com.sofia.mobile.ui.viewmodels.PatientViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun PatientForm(){
    var currentStep by remember { mutableStateOf(0) }
    val apiService = RetrofitInstance.api
    val pacienteRepository = PacienteRepository(apiService)
    val pvm: PatientViewModel = viewModel(factory = PatientViewModelFactory(pacienteRepository))
    val courotineScope = rememberCoroutineScope()
    var snackbarMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormProgress(currentStep)
        when(currentStep){
            0 -> FormInfo(
                pvm = pvm,
                onNext = { currentStep++ }
            )
            1 -> FormPerfil(
                pvm = pvm,
                onNext = { currentStep++ }
            )
            2 -> FormResponsavel(
                pvm = pvm,
                onNext = { currentStep++ }
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
                            }
                        })
                    }
                }
                1 -> {
                    CustomButton(text = "Voltar", onClick = {
                        currentStep--
                    })
                    CustomButton(text = "Próximo", onClick = { currentStep++ })
                }
                2 -> {
                    CustomButton(text = "Voltar", onClick = { currentStep-- })
                    CustomButton(text = "Salvar", onClick = {
                        courotineScope.launch {
                            try{
                                snackbarMessage = pvm.sendData()
                            }catch(e: Exception) {
                                // Registre o erro aqui
                                Log.e("PatientForm", "Ocorreu um erro ao enviar os dados", e)
                            }
                        }
                    })

                }
            }
        }
        if(snackbarMessage.isNotEmpty()) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { snackbarMessage = "" }) {
                        Text("Fechar")
                    }
                }
            ) {
                Text(snackbarMessage)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInfo(
    pvm: PatientViewModel,
    onNext: () -> Unit
){
    val nome by pvm.nome.collectAsState()
    val sobrenome by pvm.sobrenome.collectAsState()
    val sexo by pvm.sexo.collectAsState()
    val etnia by pvm.etnia.collectAsState()


    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImagePicker()

            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = nome,
                onValueChange = { pvm.updateNome(it) },
                label = { Text("Nome") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "Primeiro Nome")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )

            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = sobrenome,
                onValueChange = { pvm.updateSobrenome(it) },
                label = { Text("Sobrenome") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "Sobrenome")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )

            OutlineTextRadioButton(
                label = "Sexo",
                options = Sexo.values().toList(),
                pvm = pvm
            )

            CustomDatePicker(pvm = pvm)
            val options = Etnia.values().map { it.name }
            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }

            ExposedDropdownMenuBox(
                modifier = Modifier,
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(264.dp)
                        .menuAnchor(),
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    value = etnia?.let { it.toString() } ?: "",
                    onValueChange = {},
                    label = { Text("Etnia") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrillantPurple,
                        unfocusedBorderColor = BrillantPurple,
                        unfocusedTextColor = BrillantPurple,
                        focusedLabelColor = BrillantPurple
                    )
                )

                ExposedDropdownMenu(
                    modifier = Modifier,
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            modifier = Modifier,
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                                pvm.updateEtnia(Etnia.valueOf(selectionOption))
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FormPerfil(
    pvm: PatientViewModel,
    onNext: () -> Unit
) {
    val casosFamilia = pvm.casosFamilia.collectAsState()
    val complicacoesGravidez = pvm.complicacoesGravidez.collectAsState()
    val prematuro = pvm.prematuro.collectAsState()

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlineRadioButton(
                label = "Nasceu prematuro?",
                options = listOf("Sim", "Não"),
                state = prematuro,
                onOptionSelected = pvm::updatePrematuro
            )

            OutlineRadioButton(
                label = "Há alguém na família diagnosticado com autismo?",
                options = listOf("Sim", "Não"),
                state = casosFamilia,
                onOptionSelected = pvm::updateCasosFamilia
            )

            OutlineRadioButton(
                label = "A mãe teve complicações na gravidez?",
                options = listOf("Sim", "Não"),
                state = complicacoesGravidez,
                onOptionSelected = pvm::updateComplicacoesGravidez
            )

        }
    }
}

@Composable
fun FormResponsavel(
    pvm: PatientViewModel,
    onNext: () -> Unit
) {
    val nomeResponsavel by pvm.nomeResponsavel.collectAsState()
    val sobrenomeResponsavel by pvm.sobrenomeResponsavel.collectAsState()
    val celularResponsavel by pvm.celular.collectAsState()
    val emailResponsavel by pvm.email.collectAsState()

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = nomeResponsavel,
                onValueChange = { pvm.updateNomeResponsavel(it) },
                label = { Text("Nome") },
                enabled = true,
                readOnly = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "Primeiro Nome")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )

            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = sobrenomeResponsavel,
                onValueChange = { pvm.updateSobrenomeResponsavel(it) },
                label = { Text("Nome") },
                enabled = true,
                readOnly = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "Primeiro Nome")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )

            //Selectbox(label = "Parentesco", listOf("Mãe", "Pai", "Irmão(ã)"))
        }
    }
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = celularResponsavel,
                onValueChange = { pvm.updateCelular(it) },
                label = { Text("Email") },
                enabled = true,
                readOnly = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )
            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = emailResponsavel,
                onValueChange = { pvm.updateEmail(it) },
                label = { Text("Email") },
                enabled = true,
                readOnly = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )
        }
    }
}

fun isFormInfoValid(pvm: PatientViewModel): Boolean {
    return pvm.nome.value.isNotEmpty() &&
            pvm.sobrenome.value.isNotEmpty()
}

@Composable
fun FormProgress(currentStep: Int) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .height(80.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            when(currentStep){
                0 -> FormProgressStep1()
                1 -> FormProgressStep2()
                2 -> FormProgressStep3()
            }
        }
    }
}

@Composable
fun FormProgressStep1(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step1),
            contentDescription = null
        )
        Text(text = "Informações", style = fs12.copy(Gray1))
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "2", style = body2.copy(Gray1))
        Text(text = "Perfil", style = fs12.copy(Gray1))
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "3", style = body2.copy(Gray1))
        Text(text = "Responsável", style = fs12.copy(Gray1))
    }
}

@Composable
fun FormProgressStep2(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step_check),
            contentDescription = null
        )
        Text(text = "Informações", style = fs12.copy(Gray1))
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step2),
            contentDescription = null
        )
        Text(text = "Perfil", style = fs12.copy(Gray1))
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "3", style = body2.copy(Gray1))
        Text(text = "Responsável", style = fs12.copy(Gray1))
    }
}

@Composable
fun FormProgressStep3(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step_check),
            contentDescription = null
        )
        Text(text = "Informações", style = fs12.copy(Gray1))
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step_check),
            contentDescription = null
        )
        Text(text = "Perfil", style = fs12.copy(Gray1))
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step3),
            contentDescription = null
        )
        Text(text = "Responsável", style = fs12.copy(Gray1))
    }
}

/*
@Preview
@Composable
fun FormInfoPreview(){
    FormInfo()
}


@Preview
@Composable
fun FormProgressPreview(){
    FormProgress(1)
}

*/