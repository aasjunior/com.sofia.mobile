package com.sofia.mobile.ui.components.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.sofia.mobile.ui.components.inputs.OutlineTextRadioButton
import com.sofia.mobile.ui.components.inputs.Selectbox

@Composable
fun PatientForm(){
    var currentStep by remember { mutableStateOf(0) }
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.8f),
        shape = RoundedCornerShape(20.dp)
    ) {
        FormInfo()

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInfo(){
    val ethnicities = listOf("Branca", "Parda", "Preta", "Amarela", "Indígena")
    var state by remember { mutableStateOf(true) }
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ImagePicker()

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = text,
            onValueChange = { text = it },
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
                // Customize colors here
            )
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.8f),
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
                // Customize colors here
            )
        )

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = 1685112333816, // epoch/unix timestamp
            initialDisplayMode = DisplayMode.Input,
        )

        DatePicker(
            modifier = Modifier.fillMaxWidth(0.8f),
            state = datePickerState,
            showModeToggle = false,
            headline = null,
            title = null,
        )

        Selectbox(label = "Etnia", options = ethnicities)

        val state = remember { mutableIntStateOf(0) }
        OutlineTextRadioButton(
            label = "Sexo",
            options = listOf("Feminino", "Masculino"),
            state = state
        )
    }
}

@Preview
@Composable
fun FormInfoPreview(){
    FormInfo()
}

/*
@Preview
@Composable
fun PatientFormPreview(){
    PatientForm()
}*/