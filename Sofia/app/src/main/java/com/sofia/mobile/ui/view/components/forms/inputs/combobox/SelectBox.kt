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
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.CustomTextField
import kotlin.reflect.KClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified T: Enum<T>> SelectBox(
    selectedValue: T?,
    crossinline updateValue: (T) -> Unit,
    labelId: Int,
    enumClass: KClass<T>
){
    val options = enumClass.java.enumConstants!!.map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(selectedValue?.name ?: options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {

        CustomTextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            label = stringResource(id = labelId),
            value = selectedValue.toString(),
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
                        updateValue(enumValueOf(selectionOption))
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
