package com.sofia.mobile.ui.components.forms

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sofia.mobile.R
import com.sofia.mobile.ui.components.buttons.CustomButton
import com.sofia.mobile.ui.components.inputs.OutlineRadioButton
import com.sofia.mobile.ui.components.inputs.OutlineTextRadioButton
import com.sofia.mobile.ui.components.text.body2
import com.sofia.mobile.ui.components.text.fs12
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Gray1
import com.sofia.mobile.ui.viewmodels.PatientInfoViewModel

@Composable
fun PatientForm(){
    var currentStep by remember { mutableStateOf(0) }
    val pvm: PatientInfoViewModel = viewModel()

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
                    CustomButton(text = "Salvar", onClick = {})
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInfo(
    pvm: PatientInfoViewModel,
    onNext: () -> Unit
){
    val nome by pvm.nome.collectAsState()
    val sobrenome by pvm.sobrenome.collectAsState()
    val sexo by pvm.sexo.collectAsState()
    val etnia by pvm.etnia.collectAsState()
    val ethnicities = listOf("Branca", "Parda", "Preta", "Amarela", "Indígena")

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
                options = listOf("Feminino", "Masculino"),
                pvm = pvm
            )
/*
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Input,
            )
            DatePicker(
                modifier = Modifier.widthIn(min = 264.dp),
                state = datePickerState,
                showModeToggle = false,
                headline = null,
                title = null
            )

            Selectbox(label = "Etnia", options = ethnicities, selectedOptionVM = etnia)
*/
        }
    }
}

@Composable
fun FormPerfil(
    pvm: PatientInfoViewModel,
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
    pvm: PatientInfoViewModel,
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

fun isFormInfoValid(pvm: PatientInfoViewModel): Boolean {
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
                3 -> FormProgressStep3()
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