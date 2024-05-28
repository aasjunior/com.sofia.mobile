package com.sofia.mobile.ui.view.components.forms.inputs.pickers

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.SoftPurple
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.CustomTextField
import com.sofia.mobile.ui.viewmodel.PatientViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(pvm: PatientViewModel) {
    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val birthDate by pvm.patientState.value.birthDate.collectAsState()


    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val selectedDate = Instant
                        .ofEpochMilli(utcTimeMillis)
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate()

                return !selectedDate.isAfter(LocalDate.now())
            }
        }
    )
    var selectedDate by remember { mutableStateOf("") }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                selectedDate = millis.toBrazilianDateFormat()
                                pvm.patientState.value
                                    .updateBirthDate(
                                        Instant
                                            .ofEpochMilli(millis)
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate()
                                            .toString()
                                    )
                            }
                        showDatePickerDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(BrillantPurple)
                ) {
                    Text(text = "Selecionar data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    CustomTextField(
        label = stringResource(id = R.string.patient_form_birth),
        value = birthDate.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            ),
        onValueChange = {},
        readOnly = true,
        modifier = Modifier.onFocusEvent {
                if(it.isFocused){
                    showDatePickerDialog = true
                    focusManager.clearFocus(force = true)
                }
            }
    )
}

private fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}