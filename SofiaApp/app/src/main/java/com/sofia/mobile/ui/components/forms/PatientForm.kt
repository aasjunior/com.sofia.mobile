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
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
fun PatientForm(){
    var currentStep by remember { mutableStateOf(0) }
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.8f),
        shape = RoundedCornerShape(20.dp)
    ) {
        FormStep1()

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormStep1(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ImagePicker()
        var text by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier,
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
            modifier = Modifier,
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
            state = datePickerState,
            showModeToggle = false,
            headline = null,
            title = null,
        )
    }
}

@Preview
@Composable
fun FormStep1Preview(){
    FormStep1()
}

/*
@Preview
@Composable
fun PatientFormPreview(){
    PatientForm()
}*/