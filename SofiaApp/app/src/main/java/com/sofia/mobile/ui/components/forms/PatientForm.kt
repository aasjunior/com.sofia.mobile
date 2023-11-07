package com.sofia.mobile.ui.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.components.inputs.ImagePicker
import androidx.compose.material3.DatePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableIntStateOf
import com.sofia.mobile.ui.components.buttons.CustomButton
import com.sofia.mobile.ui.components.inputs.OutlineRadioButton
import com.sofia.mobile.ui.components.inputs.OutlineTextRadioButton
import com.sofia.mobile.ui.components.inputs.Selectbox
import com.sofia.mobile.ui.theme.BrillantPurple

@Composable
fun PatientForm(){
    var currentStep by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*when(currentStep){
            0 -> FormInfo { currentStep++ }
            1 -> FormPerfil { currentStep++ }
        }*/
        FormPerfil {currentStep++}

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            if( currentStep == 0)
                CustomButton(text = "Voltar", onClick = {})
            CustomButton(text = "Próximo", onClick = {})
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInfo(onNext: () -> Unit){
    val ethnicities = listOf("Branca", "Parda", "Preta", "Amarela", "Indígena")
    var text by rememberSaveable { mutableStateOf("") }

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
            ImagePicker()

            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = text,
                onValueChange = { text = it },
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
                value = text,
                onValueChange = { text = it },
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

            val state = remember { mutableIntStateOf(0) }
            OutlineTextRadioButton(
                label = "Sexo",
                options = listOf("Feminino", "Masculino"),
                state = state
            )

            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
                initialDisplayMode = DisplayMode.Input,
            )
            Box(modifier = Modifier.width(264.dp)) {
                DatePicker(
                    modifier = Modifier.width(264.dp),
                    state = datePickerState,
                    showModeToggle = false,
                    headline = null,
                    title = null
                )
            }

            Selectbox(label = "Etnia", options = ethnicities)
        }
    }
}

@Composable
fun FormPerfil(onNext: () -> Unit) {
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

            val stateP1 = remember { mutableIntStateOf(0) }
            OutlineRadioButton(
                label = "Nasceu prematuro?",
                options = listOf("Sim", "Não"),
                state = stateP1
            )
            val stateP2 = remember { mutableIntStateOf(0) }
            OutlineRadioButton(
                label = "Há alguém na família diagnosticado com autismo?",
                options = listOf("Sim", "Não"),
                state = stateP2
            )
            val stateP3 = remember { mutableIntStateOf(0) }
            OutlineRadioButton(
                label = "A mãe teve complicações na gravidez?",
                options = listOf("Sim", "Não"),
                state = stateP3
            )

        }
    }
}

@Composable
fun FormResponsavel(onNext: () -> Unit) {
    var nomeResponsavel by rememberSaveable { mutableStateOf("") }
    var sobrenomeResponsavel by rememberSaveable { mutableStateOf("") }
    var celularResponsavel by rememberSaveable { mutableStateOf("") }
    var emailResponsavel by rememberSaveable { mutableStateOf("") }

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
                value = nomeResponsavel,
                onValueChange = { nomeResponsavel = it },
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
                onValueChange = { sobrenomeResponsavel = it },
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

            Selectbox(label = "Parentesco", listOf("Mãe", "Pai", "Irmão(ã)"))
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
                onValueChange = { celularResponsavel = it },
                label = { Text("Celular") },
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
                onValueChange = { emailResponsavel = it },
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
/*
@Preview
@Composable
fun FormInfoPreview(){
    FormInfo()
}*/



@Preview
@Composable
fun PatientFormPreview(){
    PatientForm()
}