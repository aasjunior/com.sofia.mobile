package com.sofia.mobile.ui.view.components.forms.inputs.combobox

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sofia.mobile.R
import com.sofia.mobile.domain.common.enums.Ethnicity
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectEthnicity(
    ethnicity: Ethnicity?,
    updateEthnicity: (Ethnicity) -> Unit
){
    val options = Ethnicity.values().toList()
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        modifier = Modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {

        CustomTextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            label = stringResource(id = R.string.patient_form_ethnicity),
            value = if(ethnicity != null) stringResource(id = ethnicity.resId) else "",
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(
            modifier = Modifier,
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier,
                    text = { Text(stringResource(id = selectionOption.resId)) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        updateEthnicity(Ethnicity.valueOf(selectionOption.name))
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}