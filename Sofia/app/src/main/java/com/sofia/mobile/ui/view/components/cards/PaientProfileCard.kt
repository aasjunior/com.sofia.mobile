package com.sofia.mobile.ui.view.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.domain.model.patient.Patient
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.forms.inputs.pickers.ImagePicker
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel

@Composable
fun PatientProfileCard(
    patient: Patient,
    onClick: () -> Unit,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit
){
    FloatCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            ImagePicker(imagePickerViewModel, onPickImageClick)

            Column(Modifier.fillMaxWidth(0.7f)) {
                Text(
                    text = "${patient.firstName} ${patient.lastName}",
                    style = h3
                )
                Text(
                    text = stringResource(id = R.string.patient_age, patient.getAgeMonths()),
                    style = text1
                )
            }
            Column {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit Icon",
                        tint = BrillantPurple
                    )
                }
            }
        }
    }
}

@Composable
private fun TestHistory(patientId: String){

}
