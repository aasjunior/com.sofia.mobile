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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.domain.Parentesco
import com.sofia.mobile.domain.Sexo
import com.sofia.mobile.repository.RepositoryProvider
import com.sofia.mobile.ui.components.buttons.CustomButton
import com.sofia.mobile.ui.components.buttons.CustomOutlinedButton
import com.sofia.mobile.ui.components.inputs.CustomDatePicker
import com.sofia.mobile.ui.components.inputs.OutlinedRadioButton
import com.sofia.mobile.ui.components.inputs.OutlinedTextRadioButton
import com.sofia.mobile.ui.components.text.body2
import com.sofia.mobile.ui.components.text.fs12
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Gray1
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
                onDismissRequest = { showDialog == false },
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
                onDismissRequest = { showDialog == false },
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

            OutlinedTextRadioButton(
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

            OutlinedRadioButton(
                label = "Nasceu prematuro?",
                options = listOf("Sim", "Não"),
                state = prematuro,
                onOptionSelected = pvm::updatePrematuro
            )

            OutlinedRadioButton(
                label = "Há alguém na família diagnosticado com autismo?",
                options = listOf("Sim", "Não"),
                state = casosFamilia,
                onOptionSelected = pvm::updateCasosFamilia
            )

            OutlinedRadioButton(
                label = "A mãe teve complicações na gravidez?",
                options = listOf("Sim", "Não"),
                state = complicacoesGravidez,
                onOptionSelected = pvm::updateComplicacoesGravidez
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormResponsavel(
    pvm: PatientViewModel,
    onNext: () -> Unit
) {
    val nomeResponsavel by pvm.nomeResponsavel.collectAsState()
    val sobrenomeResponsavel by pvm.sobrenomeResponsavel.collectAsState()
    val parentesco by pvm.parentesco.collectAsState()
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
                value = sobrenomeResponsavel,
                onValueChange = { pvm.updateSobrenomeResponsavel(it) },
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

            val options = Parentesco.values().map { it.descricao }
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
                    value = parentesco?.let { it.descricao } ?: "",
                    onValueChange = {},
                    label = { Text("Parentesco") },
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
                                pvm.updateParentesco(Parentesco.values().first { it.descricao == selectionOption })
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

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
                label = { Text("Celular") },
                enabled = true,
                readOnly = false,
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
                readOnly = false,
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
            pvm.sobrenome.value.isNotEmpty() &&
            pvm.dataNascimento.value != null &&
            pvm.sexo.value != null &&
            pvm.etnia.value != null
}

fun isFormPerfilValid(pvm: PatientViewModel): Boolean {
    return pvm.casosFamilia.value != null &&
            pvm.complicacoesGravidez.value != null &&
            pvm.prematuro.value != null
}

fun isFormResponsavelValid(pvm: PatientViewModel): Boolean {
    return pvm.nomeResponsavel.value.isNotEmpty() &&
            pvm.sobrenomeResponsavel.value.isNotEmpty() &&
            pvm.celular.value.isNotEmpty() &&
            pvm.email.value.isNotEmpty() &&
            pvm.parentesco.value != null
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

@Preview
@Composable
private fun PatientFormPreview(){
    PatientForm(rememberNavController())
}