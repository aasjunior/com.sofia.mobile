package com.sofia.mobile.ui.components.inputs

import android.os.Build
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.viewmodels.PatientViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(pvm: PatientViewModel) {
    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val dataNascimento by pvm.dataNascimento.collectAsState()
    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            // Blocks future dates from being selected.
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val selectedDate = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC")).toLocalDate()
                }else{
                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calendar.timeInMillis = utcTimeMillis
                    LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                }
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
                                // Atualize a data de nascimento no ViewModel
                                pvm.updateDataNascimento(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate())
                            }
                        showDatePickerDialog = false
                    }) {
                    Text(text = "Selecionar data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    OutlinedTextField(
        value = pvm.dataNascimento.value?.let {
            it.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        } ?: selectedDate,
        onValueChange = { },
        Modifier
            .padding(8.dp)
            .width(264.dp)
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true
                    focusManager.clearFocus(force = true)
                }
            },
        label = {
            Text("Data de Nascimento")
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        readOnly = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BrillantPurple,
            unfocusedBorderColor = BrillantPurple,
            unfocusedTextColor = BrillantPurple,
            focusedLabelColor = BrillantPurple
        )
    )
}

fun Long.toBrazilianDateFormat(
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