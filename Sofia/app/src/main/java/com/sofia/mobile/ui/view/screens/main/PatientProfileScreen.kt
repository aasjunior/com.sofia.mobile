package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.domain.checklist.TestResponse
import com.sofia.mobile.domain.common.enums.Checklist
import com.sofia.mobile.domain.common.enums.ChecklistType
import com.sofia.mobile.domain.common.utils.formatRegisterDate
import com.sofia.mobile.domain.model.patient.Patient
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.buttons.CustomButton
import com.sofia.mobile.ui.view.components.cards.FloatCard
import com.sofia.mobile.ui.view.components.cards.PatientProfileCard
import com.sofia.mobile.ui.view.components.textstyles.ClickableLinkText
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h2
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.phrase
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text3
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel
import com.sofia.mobile.ui.viewmodel.PatientProfileViewModel
import java.util.Locale

@Composable
fun PatientProfileScreen(
    navController: NavController,
    patientId: String,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit
) {
    val vm: PatientProfileViewModel = viewModel()

    LaunchedEffect(key1 = patientId){
        vm.fetchPatient(patientId)
        vm.fetchTest(patientId)
    }

    val patient = vm.patient
    val test = vm.testResponse

    if(patient != null){
        Content(patient, test, imagePickerViewModel, onPickImageClick, navController)
    }else{
        Text(text = "Não encontrado")
    }


}

@Composable
private fun Content(
    patient: Patient,
    test: TestResponse?,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit,
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.patient_form_title),
                style = h3.copy(color = BrillantPurple)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ){
            item {
                PatientProfileCard(
                    patient = patient,
                    onClick = { navController.navigate("${MainNavOptions.PatientEditScreen.name}/${patient.id}") },
                    imagePickerViewModel = imagePickerViewModel,
                    onPickImageClick = onPickImageClick
                )
            }
            item {
                HistoryCard(navController, test)
            }
            item {
                ApplyNewTestCard(navController, patient.id!!)
            }
        }
    }
}

@Composable
private fun HistoryCard(
    navController: NavController,
    test: TestResponse?
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.patient_history),
            style = h3.copy(color = BrillantPurple)
        )

        FloatCard {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if(test != null){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ){
                        Column {
                            Text(
                                text = "${test.testName.deecription} | " + stringResource(id = test.testType.resId),
                                style = text3
                            )
                            Text(
                                text = formatRegisterDate(
                                    test.registerDateTime, 
                                    Locale.getDefault(),
                                    stringResource(id = R.string.patient_qchat_at)
                                ),
                                style = phrase
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            modifier = Modifier.size(105.dp),
                            painter = painterResource(
                                if(test.result) R.drawable.ic_star_signs_asd
                                else R.drawable.ic_sorrident_star_elipse
                            ),
                            contentDescription = null
                        )
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = stringResource(
                                    if(test.result) R.string.result_positive
                                    else R.string.result_negative
                                ),
                                style = h2
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    ClickableLinkText(text = stringResource(id = R.string.result_view_all_data)){
                        navController.navigate("${MainNavOptions.ResultDetailsScreen.name}/${test.testId}/${test.result}")
                    }
                }else{
                    Text(
                        text = stringResource(id = R.string.patient_empty_history),
                        style = text3
                    )
                }
            }
        }
    }
}

@Composable
private fun ApplyNewTestCard(
    navController: NavController,
    patientId: String
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.patient_test_apply),
            style = h3.copy(color = BrillantPurple)
        )

        FloatCard {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ){
                    Column {
                        Text(
                            text = "${Checklist.QChat.deecription} | " + stringResource(id = ChecklistType.SCREENING.resId),
                            style = text3
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(id = R.string.patient_qchat_description),
                            style = phrase
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                CustomButton(
                    text = stringResource(id = R.string.patient_test_apply_btn),
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(50.dp)
                ) {
                    navController.navigate("${MainNavOptions.QChatScreen.name}/$patientId")
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun HistoryCardPreview(){
    HistoryCard(test = TestResponse(
        "",
        Checklist.QChat,
        ChecklistType.SCREENING,
        "2024-05-26T16:58:40.080+00:00",
        true
    ))
}

 */