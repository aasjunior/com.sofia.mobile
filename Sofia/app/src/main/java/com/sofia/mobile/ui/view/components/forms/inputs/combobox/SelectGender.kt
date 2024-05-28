package com.sofia.mobile.ui.view.components.forms.inputs.combobox

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sofia.mobile.domain.common.enums.Gender
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles
import com.sofia.mobile.ui.viewmodel.PatientViewModel

@Composable
fun SelectGender(
    label: String,
    pvm: PatientViewModel
) {
    val gender by pvm.patientState.value.gender.collectAsState()
    val options = Gender.values().toList()

    Column(
        modifier = Modifier
            .width(264.dp)
            .border(1.dp, BrillantPurple, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            style = SofiaTextStyles.text2.copy(color = BrillantPurple),
        )

        Row(
            modifier = Modifier
                .width(264.dp)
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            options.forEach { option ->
                Row(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .selectable(
                            selected = (gender == option),
                            onClick = {
                                pvm.patientState.value
                                    .updateGender(option)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    RadioButton(
                        selected = gender == option,
                        onClick = null, // null because we're handling onClick above
                        colors = RadioButtonDefaults.colors(BrillantPurple)
                    )
                    Text(
                        text = stringResource(id = option.resId),
                        style = SofiaTextStyles.legend1.copy(color = BrillantPurple),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}