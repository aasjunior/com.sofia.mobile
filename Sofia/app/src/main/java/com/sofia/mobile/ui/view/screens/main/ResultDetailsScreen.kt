package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.domain.checklist.qchat.QChat
import com.sofia.mobile.domain.checklist.qchat.mappedQuestions
import com.sofia.mobile.domain.model.patient.Patient
import com.sofia.mobile.ui.theme.SofiaColorScheme
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.cards.FloatCard
import com.sofia.mobile.ui.view.components.cards.ResultCard
import com.sofia.mobile.ui.view.components.forms.inputs.OutlinedRadioButton
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.CustomTextField
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
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
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.result_details_title),
                    style = h3.copy(color = BrillantPurple)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item {
                        ResultCard(result)
                    }
                    item {
                        DetailsSection(patient, qchat)
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailsSection(
    patient: Patient?,
    qChat: QChat?
){
    var cardView = remember { mutableStateOf(0) }

    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        CustomTabRow(cardView)
    }

    when(cardView.value){
        0 -> {
            if(patient != null){
                ProfileCard(patient)
            }else{
                Text("Não encontrado")
            }
        }
        1 -> {
            if(qChat != null){
                ResponsesCard(qChat)
            }else{
                Text("Não encontrado")
            }
        }
    }
}

@Composable
private fun CustomTabRow(
    cardView: MutableState<Int>
) {
    val titles = listOf(R.string.result_details_profile, R.string.result_details_responses)
    var selectedTabIndex by remember { mutableStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth(0.4f),
        containerColor = Color.Transparent,
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    cardView.value = index
                },
            ) {
                Text(
                    text = stringResource(id = title),
                    color = if (selectedTabIndex == index) BrillantPurple else SofiaColorScheme.Gray1,
                    style = text1,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}

@Composable
private fun ProfileCard(patient: Patient){
    val options = listOf(
        R.string.form_yes,
        R.string.form_no
    )
    FloatCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            CustomTextField(
                label = stringResource(id = R.string.form_firstname),
                value = patient.firstName,
                onValueChange = {},
                enabled = false
            )

            CustomTextField(
                label = stringResource(id = R.string.form_lastname),
                value = patient.lastName,
                onValueChange = {},
                enabled = false
            )

            CustomTextField(
                label = stringResource(id = R.string.patient_form_gender),
                value = stringResource(id = patient.gender.resId),
                onValueChange = {},
                enabled = false
            )

            CustomTextField(
                label = stringResource(id = R.string.patient_form_birth),
                value = patient.birthDate,
                onValueChange = {},
                enabled = false
            )

            CustomTextField(
                label = stringResource(id = R.string.patient_form_ethnicity),
                value = stringResource(id = patient.ethnicity.resId),
                onValueChange = {},
                enabled = false
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

@Composable
private fun ResponsesCard(
    qChat: QChat
){
    val options = listOf(
        R.string.form_yes,
        R.string.form_no
    )

    val questions = qChat.questions
    val mappedQuestions = mappedQuestions()

    FloatCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            questions.forEach { (questionLabel, answer ) ->
                val labelId = mappedQuestions[questionLabel] ?: error("Question not found")

                OutlinedRadioButton(
                    label = stringResource(id = labelId),
                    options = options,
                    state = mappedAnswers(questionLabel, answer),
                    enabled = false,
                    onOptionSelected = {}
                )
            }
        }
    }
}

private fun mappedAnswers(questionLabel: String, value: Boolean): State<Int>{
    return when(questionLabel){
        "A10" -> toStateInt(value)
        else -> toStateInt(!value)
    }
}

private fun toStateInt(value: Boolean): State<Int> {
    return when(value){
        true -> mutableStateOf(1)
        false -> mutableStateOf(0)
    }
}