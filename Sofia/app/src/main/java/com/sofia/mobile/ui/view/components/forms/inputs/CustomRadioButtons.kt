package com.sofia.mobile.ui.view.components.forms.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.theme.SofiaColorScheme
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text3

@Composable
fun OutlinedRadioButton(
    modifier: Modifier = Modifier
        .width(264.dp),
    label: String,
    options: List<Int>,
    state: State<Int?>,
    enabled: Boolean = true,
    onOptionSelected: (Int) -> Unit
) {
    val color = setColors(enabled)

    Column(
        modifier = modifier
            .border(1.dp, color, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            style = text3.copy(color = color)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .selectable(
                            selected = (state.value == index),
                            onClick = { onOptionSelected(index) }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    RadioButton(
                        selected = state.value == index,
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = color,
                            unselectedColor = color
                        ),
                        enabled = enabled
                    )
                    Text(
                        text = stringResource(id = option),
                        style = text1.copy(color = color),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

fun State<Boolean?>.toIndex(): State<Int?> {
    return mutableStateOf(if (value == true) 0 else if (value == false) 1 else null)
}

private fun setColors(enabled: Boolean): Color {
    return when(enabled){
        true -> BrillantPurple
        false -> SofiaColorScheme.Gray1
    }
}