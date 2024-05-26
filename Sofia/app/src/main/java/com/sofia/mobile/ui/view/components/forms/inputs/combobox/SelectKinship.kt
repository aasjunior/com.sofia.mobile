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
import com.sofia.mobile.domain.common.enums.Kinship
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectKinship(
    kinship: Kinship?,
    updateKinship: (Kinship) -> Unit
){
    val options = Kinship.values().map { it.name }
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
            label = stringResource(id = R.string.patient_form_kinship),
            value = kinship.toString(),
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
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        updateKinship(Kinship.valueOf(selectionOption))
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}