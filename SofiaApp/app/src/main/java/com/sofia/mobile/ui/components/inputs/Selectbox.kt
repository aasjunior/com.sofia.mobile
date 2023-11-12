package com.sofia.mobile.ui.components.inputs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.unit.dp
import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.viewmodels.PatientViewModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSelectBox(
    options: List<Etnia>,
    label: String,
    //selectedOption: MutableState<T?>,
    pvm: PatientViewModel
    //onOptionSelected: (T) -> Unit
) {
    val etnia by pvm.etnia.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    val optionsL = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    var selectedOptionText by remember { mutableStateOf(optionsL[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            value = etnia.toString(),
            onValueChange = { },
            Modifier
                .padding(8.dp)
                .width(264.dp)
                .onFocusEvent {
                    if (it.isFocused) {
                        expanded = true
                    }
                },
            label = {
                Text(label)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            readOnly = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BrillantPurple,
                unfocusedBorderColor = BrillantPurple,
                unfocusedTextColor = BrillantPurple,
                focusedLabelColor = BrillantPurple
            ),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            optionsL.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        /*pvm.updateEtnia(option)*/
                        selectedOptionText = option
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                )
            }
        }
    }
}
