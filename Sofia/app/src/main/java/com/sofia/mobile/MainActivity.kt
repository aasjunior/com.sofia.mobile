package com.sofia.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.view.contents.containers.LocalizedContent
import com.sofia.mobile.ui.viewmodel.PatientViewModel
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SofiaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val pvm: PatientViewModel = viewModel()
                    LocalizedContent {
                        FormScreen(pvm)
                    }
                }
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormField(
    label: String,
    stateFlow: StateFlow<String>,
    onValueChange: (String) -> Unit
){
    val value by stateFlow.collectAsState()

    OutlinedTextField(
        label = { Text(label) },
        value = value,
        onValueChange = onValueChange
    )
}