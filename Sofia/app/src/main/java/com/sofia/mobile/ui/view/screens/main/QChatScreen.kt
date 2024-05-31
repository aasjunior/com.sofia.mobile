package com.sofia.mobile.ui.view.screens.main

import android.util.Log
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
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.domain.checklist.qchat.mappedQuestions
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.buttons.CustomButton
import com.sofia.mobile.ui.view.components.buttons.CustomOutlinedButton
import com.sofia.mobile.ui.view.components.cards.FloatCard
import com.sofia.mobile.ui.view.components.forms.inputs.OutlinedRadioButton
import com.sofia.mobile.ui.view.components.popup.CustomAlertDialog
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text3
import com.sofia.mobile.ui.viewmodel.QChatViewModel
import kotlinx.coroutines.launch

@Composable
fun QChatScreen(
    navController: NavController,
    patientId: String
){
    val coroutineScope = rememberCoroutineScope()
    val vm = remember { QChatViewModel(patientId) }
    val qchatState = vm.qChatState.collectAsState()

    val options = listOf(
        R.string.form_yes,
        R.string.form_no
    )
    val btnNextText = stringResource(id = R.string.btn_next)
    val btnBackText = stringResource(id = R.string.btn_back)
    val btnSubmitText = stringResource(id = R.string.btn_submit)

    val alertMessageText = stringResource(id = R.string.qchat_empty)
    var showDialog by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf(alertMessageText) }
    val errorMessage by vm.errorMessage.collectAsState()

    val questions = mappedQuestions()
    Log.i("Question", "${questions}")
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val currentQuestion = questions.keys.elementAt(currentQuestionIndex)
    val currentQuestionLabel = currentQuestion.let { questions[it] } ?: return

    val answer = qchatState.value.questions.value[currentQuestion]
    val answerIndex = if (answer == true) 0 else if (answer == false) 1 else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ){
                Text(
                    text = stringResource(id = R.string.qchat_title),
                    style = h3.copy(color = BrillantPurple)
                )

                FloatCard(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(horizontal = 12.dp)
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    val labelText = stringResource(id = currentQuestionLabel)

                    OutlinedRadioButton(
                        label = labelText,
                        options = options,
                        state = mutableStateOf(answerIndex),
                        onOptionSelected = { selected ->
                            vm.update(currentQuestion, selected == 0)
                        }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${currentQuestionIndex + 1}/${questions.size}",
                            style = text3
                        )
                    }

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            when {
                currentQuestionIndex == 0 -> {
                    CustomOutlinedButton(text = btnBackText) {
                        navController.navigate(MainNavOptions.CheckListScreen.name){
                            popUpTo(MainNavOptions.QChatScreen.name){
                                inclusive = true
                            }
                        }
                    }
                    CustomButton(text = btnNextText) {
                        if(answer != null) currentQuestionIndex++
                        else showDialog = true
                    }
                }
                currentQuestionIndex > 0 -> {
                    CustomOutlinedButton(text = btnBackText) {
                        currentQuestionIndex--
                    }
                    if(currentQuestionIndex < questions.size - 1){
                        CustomButton(text = btnNextText) {
                            if(answer != null) currentQuestionIndex++
                            else showDialog = true
                        }
                    }else{
                        CustomButton(text = btnSubmitText) {
                            if(answer != null){
                                coroutineScope.launch {
                                    try{
                                        alertMessage = vm.submit()
                                        if(alertMessage == "success"){
                                            navController.navigate(
                                                "${MainNavOptions.CheckListResultScreen.name}/${vm.response.value!!.result}"
                                            )
                                        }else{
                                            showDialog = true
                                        }
                                    }catch(e: Exception){
                                        alertMessage = e.message!!
                                        showDialog = true
                                    }
                                }
                            }else{
                                showDialog = true
                            }
                        }
                    }
                }
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

    if(errorMessage != null){
        CustomAlertDialog(
            onDismissRequest = { !showDialog },
            text = errorMessage!!
        ) {
            showDialog = false
        }
    }
}