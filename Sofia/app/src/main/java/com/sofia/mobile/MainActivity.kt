package com.sofia.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.view.MainCompose
import com.sofia.mobile.ui.view.components.form.inputs.textfields.FormField
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.viewmodel.PatientViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            val rd = RelativeDimensions(screenWidth)

            MainCompose(rd)
        }
    }
}

@Composable
fun FormScreen(pvm: PatientViewModel){
    var step by remember {
        mutableStateOf(1)
    }

    Column {
        when(step){
            1 -> FirstStageForm(pvm){
                step = 2
            }
            2 -> SecondStageForm(pvm){
                step = 1
            }
        }
    }
}

@Composable
fun FirstStageForm(
    pvm: PatientViewModel,
    onContinue: () -> Unit
){
    val patientState by pvm.patientState.collectAsState()

    Column{
        Spacer(modifier = Modifier.height(10.dp))

        FormField(
            label = stringResource(id = R.string.form_firstname),
            stateFlow = patientState.firstName,
            onValueChange = {
                patientState.updateFirstName(it)
            }
        )

        Button(onClick = onContinue) {
            Text(text = stringResource(id = R.string.btn_next))
        }
    }
}

@Composable
fun SecondStageForm(
    pvm: PatientViewModel,
    onBack: () -> Unit
){
    val guardianState by pvm.guardianState.collectAsState()

    Column {
        Spacer(modifier = Modifier.height(10.dp))

        FormField(
            label = stringResource(id = R.string.form_firstname),
            stateFlow = guardianState.firstName,
            onValueChange = {
                guardianState.updateFirstName(it)
            }
        )

        Button(onClick = onBack) {
            Text(text = stringResource(id = R.string.btn_back))
        }
    }
}
