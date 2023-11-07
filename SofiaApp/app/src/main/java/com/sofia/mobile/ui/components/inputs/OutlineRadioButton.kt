package com.sofia.mobile.ui.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Lilas
import com.sofia.mobile.ui.theme.White

@Composable
fun OutlineTextRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    options: List<String>,
    state: MutableState<Int>
) {
    Column(
        modifier = Modifier.height(120.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .border(2.dp, Lilas, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = label,
                style = h3.copy(color = BrillantPurple),
                modifier = Modifier
                    .offset(y = (-30).dp)
                    .background(White, shape = RoundedCornerShape(4.dp))
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                options.forEachIndexed { index, option ->
                    Row(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .selectable(
                                selected = (state.value == index),
                                onClick = { state.value = index }
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
}

@Composable
fun OutlineRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    options: List<String>,
    state: MutableState<Int>
) {
    Column(
        modifier = Modifier
            .border(2.dp, Lilas, RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            style = h3.copy(color = BrillantPurple),
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
                            onClick = { state.value = index }
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

@Preview
@Composable
fun OutlinedTextRadioButtonPreview(){
    val state = remember { mutableIntStateOf(0) }
    OutlineTextRadioButton(
        label = "Sexo",
        options = listOf("Feminino", "Masculino"),
        state = state
    )
}

@Preview
@Composable
fun OutlineRadioButtonPreview(){
    val state = remember { mutableIntStateOf(0) }
    OutlineRadioButton(
        label = "Nasceu prematuro?",
        options = listOf("Sim", "Não"),
        state = state
    )
}