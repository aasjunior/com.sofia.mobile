package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.Gray1
import com.sofia.mobile.ui.theme.SofiaColorScheme.SoftLilas
import com.sofia.mobile.ui.theme.SofiaColorScheme.SoftPurple
import com.sofia.mobile.ui.view.components.buttons.CustomButton
import com.sofia.mobile.ui.view.components.buttons.FloatingAddButton
import com.sofia.mobile.ui.view.components.cards.CustomOptionsCard
import com.sofia.mobile.ui.view.components.cards.PatientCheckList
import com.sofia.mobile.ui.view.components.cards.PatientList
import com.sofia.mobile.ui.view.components.popup.ConfirmAlertDialog
import com.sofia.mobile.ui.view.components.textstyles.ClickableLinkText
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
import com.sofia.mobile.ui.view.contents.containers.BaseContent
import com.sofia.mobile.ui.viewmodel.PatientListViewModel

@Composable
fun PatientListScreen(
    navController: NavController,
    drawerState: DrawerState
){
    val plvm: PatientListViewModel = viewModel()
    val patients = plvm.patients.sortedBy { it.firstName.lowercase() }
    val showDialog = remember { mutableStateOf(false) }
    val isCardOpen = remember { mutableStateOf(false) }
    val isDeleteMode = remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val successMessage = plvm.successMessage.value
    val successMessageText = stringResource(id = R.string.alert_delete_patient)
    val errorMessage = plvm.errorMessage.value

    val btnClose = stringResource(id = R.string.btn_close)

    LaunchedEffect(Unit) {
        plvm.fetchPatients()
    }

    LaunchedEffect(successMessage, errorMessage) {
        when {
            !successMessage.isNullOrEmpty() -> {
                snackbarHostState.showSnackbar(
                    message = successMessage,
                    actionLabel = btnClose
                )
                plvm.successMessage.value = null
            }
            !errorMessage.isNullOrEmpty() -> {
                snackbarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = btnClose
                )
                plvm.errorMessage.value = null
            }
        }
    }

    val options = mutableListOf<Pair<String, () -> Unit>>()
    options.add(stringResource(id = R.string.btn_new) to { navController.navigate(MainNavOptions.PatientRegisterScreen.name) })
    if(patients.isNotEmpty()){
        if(isDeleteMode.value){
            options.add(stringResource(id = R.string.btn_cancel) to {
                isDeleteMode.value = !isDeleteMode.value
                if(!isDeleteMode.value){
                    plvm.deselectAllPatients()
                }
            })
        }else{
            options.add(stringResource(id = R.string.btn_delete) to {
                isDeleteMode.value = !isDeleteMode.value
                if(!isDeleteMode.value){
                    plvm.deselectAllPatients()
                }
            })
        }
    }

    BaseContent(
        navController = navController,
        drawerState = drawerState,
        bottomBarContent = {
            if(isDeleteMode.value){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SoftLilas),
                    horizontalArrangement = Arrangement.Center
                ){
                    CustomButton(
                        text = stringResource(id = R.string.btn_delete),
                        onClick = {
                            if(plvm.totalChecked > 0)
                                showDialog.value = true
                        }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SoftLilas),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (isDeleteMode.value) {
                    Text(
                        text = stringResource(id = R.string.list_selected, plvm.totalChecked),
                        style = h3.copy(color = BrillantPurple)
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.list_title),
                        style = h3.copy(color = BrillantPurple)
                    )

                    Text(
                        text = stringResource(id = R.string.list_subtitle, patients.size),
                        style = text1.copy(color = Gray1)
                    )
                }
            }

            if(patients.isEmpty()){
                isDeleteMode.value = false
                ClickableLinkText(
                    text = stringResource(id = R.string.btn_new_patient),
                    onClick =  { navController.navigate(MainNavOptions.PatientRegisterScreen.name) }
                )
            }else {
                Row(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(isCardOpen.value){
                        CustomOptionsCard(options)
                    }else{
                        FloatingAddButton(
                            onClick = { navController.navigate(MainNavOptions.PatientRegisterScreen.name) }
                        )
                    }

                    IconButton(onClick = { isCardOpen.value = !isCardOpen.value }) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = null,
                            tint = SoftPurple
                        )
                    }
                }
            }
            if(isDeleteMode.value) {
                PatientCheckList(plvm)
            }else{
                PatientList(navController, patients)
            }
            if(showDialog.value){
                ConfirmAlertDialog(
                    title = stringResource(id = R.string.alert_confirm_delete),
                    text = stringResource(
                        id = R.string.alert_confirm_description,
                        stringResource(id = R.string.alert_confirm_delete_patient)
                    ),
                    onConfirm = {
                        plvm.deleteSelectedPatients(successMessageText)
                        showDialog.value = false
                    }
                ) {
                    showDialog.value = false
                }
            }

        }
    }
}