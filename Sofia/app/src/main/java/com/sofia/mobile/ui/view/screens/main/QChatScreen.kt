package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme
import com.sofia.mobile.ui.view.components.buttons.CustomButton
import com.sofia.mobile.ui.view.components.buttons.CustomOutlinedButton
import com.sofia.mobile.ui.view.components.cards.FloatCard
import com.sofia.mobile.ui.view.components.forms.inputs.OutlinedRadioButton
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles
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

    val questions = mappedQuestions()
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val currentQuestion = questions.keys.elementAt(currentQuestionIndex)
    val currentQuestionLabel = questions[currentQuestion]!!
    val answer = qchatState.value.questions.value[currentQuestion]
    val answerIndex = if (answer == true) 0 else if (answer == false) 1 else null

    Text(
        text = stringResource(id = R.string.qchat_title),
        style = SofiaTextStyles.h3.copy(color = SofiaColorScheme.BrillantPurple)
    )

    FloatCard {
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedRadioButton(
            label = stringResource(id = currentQuestionLabel),
            options = options,
            state =  mutableStateOf(answerIndex),
            onOptionSelected = { selected ->
                vm.update(currentQuestion, selected == 0)
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = "${currentQuestionIndex + 1}/${questions.size}")
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if(currentQuestionIndex == 0){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                CustomButton(text = btnNextText) {
                    currentQuestionIndex++
                }
            }
        }
        if(currentQuestionIndex > 0){
            CustomOutlinedButton(text = btnBackText) {
                currentQuestionIndex--
            }
        }

        if(currentQuestionIndex < questions.size - 1){
            CustomButton(text = btnNextText) {
                currentQuestionIndex++
            }
        }else{
            CustomButton(text = btnSubmitText) {
                coroutineScope.launch {
                    vm.submit()
                    if(vm.response.value != null){
                        navController.navigate(
                            "${MainNavOptions.CheckListResultScreen.name}/${vm.getAccuracy()}/${vm.getResult()}"
                        )
                    }
                }
            }
        }
    }
}


private fun mappedQuestions(): Map<String, Int>{
    return mapOf(
        "A1" to R.string.qchat_A1,
        "A2" to R.string.qchat_A2,
        "A3" to R.string.qchat_A3,
        "A4" to R.string.qchat_A4,
        "A5" to R.string.qchat_A5,
        "A6" to R.string.qchat_A6,
        "A7" to R.string.qchat_A7,
        "A8" to R.string.qchat_A8,
        "A9" to R.string.qchat_A9,
        "A10" to R.string.qchat_A10,
    )
}