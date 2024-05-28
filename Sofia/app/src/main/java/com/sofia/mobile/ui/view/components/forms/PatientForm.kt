package com.sofia.mobile.ui.view.components.forms

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.buttons.CustomButton
import com.sofia.mobile.ui.view.components.buttons.CustomOutlinedButton
import com.sofia.mobile.ui.view.components.popup.CustomAlertDialog
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel
import com.sofia.mobile.ui.viewmodel.PatientViewModel
import kotlinx.coroutines.launch

@Composable
fun PatientForm(
    navController: NavController,
    pvm: PatientViewModel,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit,
    isEditMode: Boolean = false
) {
    val courotineScope = rememberCoroutineScope()

    val btnNextText = stringResource(id = R.string.btn_next)
    val btnBackText = stringResource(id = R.string.btn_back)
    val btnSaveText = stringResource(id = R.string.btn_save)

    val labels = listOf(
        R.string.patient_form_step_01,
        R.string.patient_form_step_02,
        R.string.patient_form_step_03
    )

    val alertMessageText = stringResource(id = R.string.alert_empty_field)

    var currentStep by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialogSuccess by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf(alertMessageText) }
    var textHeader by remember { mutableStateOf(R.string.patient_form_title) }
    var successMessage by remember { mutableStateOf(R.string.alert_create_patient) }

    if(isEditMode){
        textHeader = if(currentStep == 2)
            R.string.patient_edit_guardian
        else
            R.string.patient_edit_patient
        successMessage = R.string.patient_edit_success
    } else {
        textHeader = if(currentStep == 2)
            R.string.patient_form_title
        else
            R.string.patient_form_title_02
    }

    val onSend: suspend () -> String = if (isEditMode) {
        { pvm.updatePatient() }
    } else {
        { pvm.sendData() }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = textHeader),
            style = h3.copy(color = BrillantPurple)
        )
        FormProgress(currentStep, labels)

        when(currentStep){
            0 -> FormInfo(
                pvm = pvm,
                imagePickerViewModel,
                onPickImageClick
            )
            1 -> FormPerfil(
                pvm = pvm
            )
            2 -> FormGuardian(
                pvm = pvm
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            when(currentStep) {
                0 -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        CustomButton(text = btnNextText, onClick = {
                            if(isFormInfoValid(pvm.patientState.value)) {
                                currentStep++
                            }else{
                                showDialog = true
                            }
                        })
                    }
                }
                1 -> {
                    CustomOutlinedButton(text = btnBackText, onClick = {
                        currentStep--
                    })
                    CustomButton(text = btnNextText, onClick = {
                        if(isFormPerfilValid(pvm.patientState.value)) {
                            currentStep++
                        }else{
                            showDialog = true
                        }
                    })
                }
                2 -> {
                    CustomOutlinedButton(text = btnBackText, onClick = {
                        currentStep--
                    })
                    CustomButton(text = btnSaveText, onClick = {
                        if(isFormGuardianValid(pvm.guardianState.value, pvm.kinship.value)) {
                            courotineScope.launch {
                                try{
                                    alertMessage = onSend()
                                    if(alertMessage == "success"){
                                        showDialogSuccess = true
                                    }else{
                                        showDialog = true
                                    }

                                }catch(e: Exception) {
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
            CustomAlertDialog(
                onDismissRequest = { !showDialog },
                text = alertMessage
            ) {
                showDialog = false
            }
        }

        if(showDialogSuccess){
            CustomAlertDialog(
                onDismissRequest = { !showDialog },
                text = stringResource(id = successMessage)
            ) {
                navController.navigate(MainNavOptions.PatientListScreen.name){
                    popUpTo(MainNavOptions.PatientRegisterScreen.name){
                        inclusive = true
                    }
                }
                showDialog = false
            }
        }
    }
}