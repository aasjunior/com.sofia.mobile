package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.domain.model.patient.Patient
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.cards.FloatLazyCard
import com.sofia.mobile.ui.view.components.cards.ResultCard
import com.sofia.mobile.ui.view.components.forms.inputs.OutlinedRadioButton
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.CustomTextField
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.viewmodel.ResultDetailsViewModel

@Composable
fun ResultDetailsScreen(
    navController: NavController,
    testId: String,
    result: Boolean
) {
    val vm: ResultDetailsViewModel = viewModel()

    LaunchedEffect(key1 = testId){
        vm.fetchQChat(testId)
    }

    val qchat = vm.qChatResponses
    val patient = vm.patient

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.result_details_title),
                    style = h3.copy(color = BrillantPurple)
                )
                Spacer(modifier = Modifier.height(12.dp))
                ResultCard(result)
                Spacer(modifier = Modifier.height(12.dp))
                if(patient != null){
                    ProfileCard(patient)
                }else{
                    Text("Não encontrado")
                }
            }
        }
    }
}

@Composable
fun ProfileCard(patient: Patient){
    val options = listOf(
        R.string.form_yes,
        R.string.form_no
    )

    FloatLazyCard {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            CustomTextField(
                label = stringResource(id = R.string.form_firstname),
                value = patient.firstName,
                onValueChange = {},
                readOnly = true
            )

            CustomTextField(
                label = stringResource(id = R.string.form_lastname),
                value = patient.lastName,
                onValueChange = {},
                readOnly = true
            )

            CustomTextField(
                label = stringResource(id = R.string.patient_form_gender),
                value = stringResource(id = patient.gender.resId),
                onValueChange = {},
                readOnly = true
            )

            CustomTextField(
                label = stringResource(id = R.string.patient_form_birth),
                value = patient.birthDate,
                onValueChange = {},
                readOnly = true
            )

            CustomTextField(
                label = stringResource(id = R.string.patient_form_ethnicity),
                value = stringResource(id = patient.ethnicity.resId),
                onValueChange = {},
                readOnly = true
            )

             OutlinedRadioButton(
                label = stringResource(id = R.string.patient_form_q01),
                options = options,
                state = toStateInt(patient.premature),
                enabled = false,
                onOptionSelected = { }
            )

            OutlinedRadioButton(
                label = stringResource(id = R.string.patient_form_q02),
                options = options,
                state = toStateInt(patient.familyCases),
                enabled = false,
                onOptionSelected = {},
            )

            OutlinedRadioButton(
                label = stringResource(id = R.string.patient_form_q03),
                options = options,
                state = toStateInt(patient.pregnancyComplications),
                enabled = false,
                onOptionSelected = { }
            )
        }
    }
}

private fun toStateInt(value: Boolean): State<Int> {
    return when(value){
        true -> mutableStateOf(1)
        false -> mutableStateOf(0)
    }
}