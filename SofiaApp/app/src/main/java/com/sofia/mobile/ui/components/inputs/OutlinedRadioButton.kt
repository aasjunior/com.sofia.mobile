package com.sofia.mobile.ui.components.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sofia.mobile.domain.Sexo
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.body2
import com.sofia.mobile.ui.components.text.fs12
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.viewmodels.PatientViewModel

@Composable
fun OutlinedTextRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    options: List<Sexo>,
    pvm: PatientViewModel
) {
    val sexo by pvm.sexo.collectAsState()
    Column(
        modifier = Modifier
            .width(264.dp)
            .border(1.dp, BrillantPurple, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            style = body2.copy(color = BrillantPurple),
        )

        Row(
            modifier = Modifier.width(264.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            options.forEach { option ->
                Row(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .selectable(
                            selected = (sexo == option),
                            onClick = { pvm.updateSexo(option) }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    RadioButton(
                        selected = sexo == option,
                        onClick = null // null because we're handling onClick above
                    )
                    Text(
                        text = option.value,
                        style = fs12.copy(color = BrillantPurple),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OutlinedRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    options: List<String>,
    state: State<Int?>,
    onOptionSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(264.dp)
            .border(1.dp, BrillantPurple, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            style = body1.copy(color = BrillantPurple),
        )

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
                        onClick = null // null because we're handling onClick above
                    )
                    Text(
                        text = option,
                        style = body1.copy(color = BrillantPurple),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}