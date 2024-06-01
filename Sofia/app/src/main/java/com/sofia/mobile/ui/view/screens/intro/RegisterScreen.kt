package com.sofia.mobile.ui.view.screens.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.domain.model.login.LoginState
import com.sofia.mobile.R
import com.sofia.mobile.domain.model.user.UserState
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.ConfirmPassword
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.CustomTextField
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.EmailTextField
import com.sofia.mobile.ui.view.components.popup.CustomAlertDialog
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.view.contents.containers.IntroContent
import com.sofia.mobile.ui.viewmodel.LoginViewModel
import com.sofia.mobile.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    relativeDimensions: RelativeDimensions
){
    val loginState by loginViewModel.loginState.collectAsState()

    LaunchedEffect(loginState){
        if(loginState is LoginState.Success){
            navController.navigate(NavRoutes.MainRoute.name){
                popUpTo(NavRoutes.IntroRoute.name){
                    inclusive = true
                }
            }
        }
    }

    IntroContent(navController = navController, isRegister = true) {
        FormRegister(loginViewModel, relativeDimensions)
        when(val state = loginState){
            is LoginState.Loading -> { Text(text = "Validando") }
            is LoginState.Success -> { Text(text = "Logado") }
            is LoginState.Error -> { Text(text = "Erro: ${state.error}") }
            else -> Text(text = "")
        }
    }
}

@Composable
private fun FormRegister(
    lvm: LoginViewModel,
    rd: RelativeDimensions
){
    val coroutineScope = rememberCoroutineScope()
    val uvm: UserViewModel = viewModel()
    val us = uvm.userState.value
    var showDialog by remember { mutableStateOf(false) }
    var showDialogSuccess by remember { mutableStateOf(false) }
    val alertEmptyFields = stringResource(id = R.string.alert_empty_field)
    var alertMessage by remember { mutableStateOf<String?>(null) }
    var currentStep by remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when(currentStep){
            0 -> {
                FirstStep(us, rd)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    horizontalArrangement = Arrangement.End
                ){
                    Button(
                        modifier = Modifier
                            .width(145.dp)
                            .height(45.dp),
                        onClick = {
                            if(us.firstName.value.isNotEmpty() && us.lastName.value.isNotEmpty()) currentStep++
                            else{
                                alertMessage = alertEmptyFields
                                showDialog = true
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.btn_next))
                    }
                }
            }
            1 -> {
                SecondStep(us, rd)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    OutlinedButton(
                        modifier = Modifier
                            .width(145.dp)
                            .height(45.dp),
                        onClick = { currentStep-- }
                    ) {
                        Text(text = stringResource(id = R.string.btn_back))
                    }
                    Button(
                        modifier = Modifier
                            .width(145.dp)
                            .height(45.dp),
                        onClick = {
                            if(us.email.value.isNotEmpty() && us.password.value.isNotEmpty()){
                                coroutineScope.launch {
                                    try{
                                        alertMessage = uvm.sendData()
                                        if(alertMessage == "success"){
                                            lvm.login(
                                                uvm.user!!.username,
                                                us.password.value
                                            )
                                        }
                                    }catch(e: Exception){
                                        uvm.errorMessage.value = e.message
                                    }
                                }
                            }
                            else{
                                alertMessage = alertEmptyFields
                                showDialog = true
                            }

                        }
                    ) {
                        Text(text = stringResource(id = R.string.btn_register))
                    }
                }
            }
        }
    }

    if(uvm.errorMessage.value != null){
        alertMessage = uvm.errorMessage.value
        showDialog = true
    }

    if(showDialog){
        CustomAlertDialog(
            onDismissRequest = { !showDialog },
            text = alertMessage!!
        ) {
            showDialog = false
        }
    }

}

@Composable
private fun FirstStep(
    us: UserState,
    rd: RelativeDimensions
){
    val firstName by us.firstName.collectAsState()
    val lastName by us.lastName.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        CustomTextField(
            label = stringResource(id = R.string.form_firstname),
            value = firstName,
            roundedCornerShape = true,
            width = rd.formFieldIntro,
            onValueChange = {
                us.updateFirstName(it!!)
            }
        )
        CustomTextField(
            label = stringResource(id = R.string.form_lastname),
            value = lastName,
            roundedCornerShape = true,
            width = rd.formFieldIntro,
            onValueChange = {
                us.updateLastName(it!!)
            }
        )
    }
}

@Composable
private fun SecondStep(
    us: UserState,
    rd: RelativeDimensions
){
    val email by us.email.collectAsState()
    val password by us.password.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EmailTextField(
            email = email,
            onValueChange = { us.updateEmail(it) },
            roundedCornerShape = true,
            width = rd.formFieldIntro
        )
        ConfirmPassword(
            password = password,
            onValueChange = { us.updatePassword(it!!) },
            width = rd.formFieldIntro
        )
    }
}