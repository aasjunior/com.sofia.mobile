package com.sofia.mobile.ui.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.body2
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Lilas
import com.sofia.mobile.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Selectbox(label: String, options: List<String>, selectedOptionVM: String) {
    var selectedOptionText = selectedOptionVM
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .width(264.dp)
            .height(100.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
               // .border(1.dp, BrillantPurple, RoundedCornerShape(12.dp))
                //.padding(16.dp)
                //.zIndex(0f)
        ) {
/*            Text(
                text = label,
                style = h3.copy(color = BrillantPurple),
                modifier = Modifier
                    .offset(y = (-30).dp)
                    .zIndex(1f)
                    .background(White, shape = RoundedCornerShape(4.dp))
            )*/
            ExposedDropdownMenuBox(
                modifier = Modifier,
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    label = {Text(label)},
                    value = selectedOptionText,
                    onValueChange = {},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    //colors = ExposedDropdownMenuDefaults.textFieldColors()
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
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectboxPreview(){
    val ethnicities = listOf("Branca", "Parda", "Preta", "Amarela", "Indígena")
    Selectbox(label = "Etnia", options = ethnicities, selectedOptionVM = "Branca")
}