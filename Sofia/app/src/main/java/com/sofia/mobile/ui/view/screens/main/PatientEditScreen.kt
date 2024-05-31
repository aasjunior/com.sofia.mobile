package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.ui.theme.SofiaColorScheme
import com.sofia.mobile.ui.view.components.forms.PatientForm
import com.sofia.mobile.ui.view.components.popup.CustomAlertDialog
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel
import com.sofia.mobile.ui.viewmodel.PatientViewModel

@Composable
fun PatientEditScreen(
    navController: NavController,
    patientId: String,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit
){
    val pvm: PatientViewModel = viewModel()
    var showDialog by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = patientId){
        try {
            pvm.fetchPatient(patientId)
        }catch(e: Exception){
            alertMessage = e.message!!
            showDialog = true
        }
    }

    if(showDialog) {
        CustomAlertDialog(
            onDismissRequest = { !showDialog },
            text = alertMessage
        ) {
            showDialog = false
        }
    }

    if(pvm.errorMessage.value != null){
        CustomAlertDialog(
            onDismissRequest = { !showDialog },
            text = pvm.errorMessage.value!!
        ) {
            showDialog = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SofiaColorScheme.SoftLilas)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Spacer(modifier = Modifier.height(12.dp))
        PatientForm(navController, pvm, imagePickerViewModel, onPickImageClick, isEditMode = true)
    }
}