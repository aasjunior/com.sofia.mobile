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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.SoftLilas
import com.sofia.mobile.ui.view.components.forms.PatientForm
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel
import com.sofia.mobile.ui.viewmodel.PatientViewModel

@Composable
fun PatientRegisterScreen(
    navController: NavController,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit
){
    val pvm: PatientViewModel = viewModel()
    pvm.errorMessage.value = stringResource(id = R.string.error_send_data)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftLilas)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Spacer(modifier = Modifier.height(12.dp))
        PatientForm(navController, pvm, imagePickerViewModel, onPickImageClick)
    }
}