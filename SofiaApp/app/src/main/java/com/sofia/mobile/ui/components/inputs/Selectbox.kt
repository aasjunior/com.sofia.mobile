package com.sofia.mobile.ui.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.body2

@Composable
fun Selectbox(label: String, items: List<String>) {
    var selectedItems by remember { mutableStateOf(List(items.size) { false }) }

    Column {
        Text(text = label, style = body2)
        items.forEachIndexed { index, item ->
            Row(Modifier.padding(vertical = 8.dp)) {
                Checkbox(
                    checked = selectedItems[index],
                    onCheckedChange = { isChecked ->
                        selectedItems = selectedItems.mapIndexed { i, isSelected ->
                            if (i == index) isChecked else isSelected
                        }
                    }
                )
                Text(
                    text = item,
                    style = body1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectboxPreview(){
    val ethnicities = listOf("Branca", "Parda", "Preta", "Amarela", "Indígena")
    Selectbox(label = "Etnia", items = ethnicities)
}