package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.view.components.cards.FloatCard
import com.sofia.mobile.ui.view.components.cards.FloatLazyCard
import com.sofia.mobile.ui.view.components.forms.inputs.OutlinedRadioButton
import com.sofia.mobile.ui.viewmodel.QChatViewModel

@Composable
fun QChatScreen(
    navController: NavController,
    patientId: String
){
    val qChatViewModel = remember { QChatViewModel(patientId) }
    val qChatState = qChatViewModel.qChatState.collectAsState()

    val options = listOf(
        R.string.form_yes,
        R.string.form_no
    )

    val questions = mappedQuestions()

    FloatLazyCard {
        for ((id, question) in questions) {
            val answer = qChatState.value.questions.value[id]
            val answerIndex = if (answer == true) 0 else if (answer == false) 1 else null

            OutlinedRadioButton(
                label = stringResource(id = question),
                options = options,
                state =  mutableStateOf(answerIndex),
                onOptionSelected = { selected ->
                    qChatViewModel.update(id, selected == 0)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
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