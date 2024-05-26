package com.sofia.mobile.ui.view.components.forms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.domain.common.enums.Ethnicity
import com.sofia.mobile.domain.common.enums.Kinship
import com.sofia.mobile.domain.model.guardian.GuardianState
import com.sofia.mobile.domain.model.patient.PatientState
import com.sofia.mobile.ui.view.components.cards.FloatCard
import com.sofia.mobile.ui.view.components.forms.inputs.OutlinedRadioButton
import com.sofia.mobile.ui.view.components.forms.inputs.combobox.SelectBox
import com.sofia.mobile.ui.view.components.forms.inputs.combobox.SelectGender
import com.sofia.mobile.ui.view.components.forms.inputs.combobox.SelectKinship
import com.sofia.mobile.ui.view.components.forms.inputs.pickers.CustomDatePicker
import com.sofia.mobile.ui.view.components.forms.inputs.pickers.ImagePicker
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.CustomTextField
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.EmailTextField
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.FormField
import com.sofia.mobile.ui.view.components.forms.inputs.textfields.PhoneTextField
import com.sofia.mobile.ui.view.components.forms.inputs.toIndex
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel
import com.sofia.mobile.ui.viewmodel.PatientViewModel

@Composable
fun FormInfo(
    pvm: PatientViewModel,
    imagePickerViewModel: ImagePickerViewModel,
    onPickImageClick: () -> Unit
){
    val firstName by pvm.patientState.value.firstName.collectAsState()
    val lastName by pvm.patientState.value.lastName.collectAsState()
    val ethnicity by pvm.patientState.value.ethnicity.collectAsState()

    FloatCard {
       Spacer(modifier = Modifier.height(10.dp))
       ImagePicker(imagePickerViewModel, onPickImageClick)

       CustomTextField(
           label = stringResource(id = R.string.form_firstname),
           value = firstName,
           onValueChange = {
               pvm.patientState.value
                   .updateFirstName(it!!)
           }
       )

       CustomTextField(
           label = stringResource(id = R.string.form_lastname),
           value = lastName,
           onValueChange = {
               pvm.patientState.value
                   .updateLastName(it!!)
           }
       )

       SelectGender(
           label = stringResource(id = R.string.patient_form_gender),
           pvm = pvm
       )

       CustomDatePicker(pvm = pvm)

        SelectBox(
            selectedValue = ethnicity,
            updateValue = pvm.patientState.value::updateEthnicity,
            labelId = R.string.patient_form_ethnicity,
            enumClass = Ethnicity::class
        )

       Spacer(modifier = Modifier.height(10.dp))
    }

}

@Composable
fun FormPerfil(
    pvm: PatientViewModel
){
    val familyCases = pvm.patientState.value.familyCases.collectAsState()
    val pregnancyComplications = pvm.patientState.value.pregnancyComplications.collectAsState()
    val premature = pvm.patientState.value.premature.collectAsState()
    val options = listOf(
        stringResource(id = R.string.form_yes),
        stringResource(id = R.string.form_no)
    )

    FloatCard {
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedRadioButton(
            label = stringResource(id = R.string.patient_form_q01),
            options = options,
            state = premature.toIndex(),
            onOptionSelected = { index ->
                pvm.patientState.value
                    .updatePremature(index == 0)
            }
        )

        OutlinedRadioButton(
            label = stringResource(id = R.string.patient_form_q02),
            options = options,
            state = familyCases.toIndex(),
            onOptionSelected = { index ->
                pvm.patientState.value
                    .updateFamilyCases(index == 0)
            }
        )

        OutlinedRadioButton(
            label = stringResource(id = R.string.patient_form_q03),
            options = options,
            state = pregnancyComplications.toIndex(),
            onOptionSelected = { index ->
                pvm.patientState.value
                    .updatePregnancyComplications(index == 0)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun FormGuardian(
    pvm: PatientViewModel
) {
    val firstName by pvm.guardianState.value.firstName.collectAsState()
    val lastName by pvm.guardianState.value.lastName.collectAsState()
    val phone by pvm.guardianState.value.phone.collectAsState()
    val email by pvm.guardianState.value.email.collectAsState()
    val kinship by pvm.kinship.collectAsState()

    FloatCard {
        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            label = stringResource(id = R.string.form_firstname),
            value = firstName,
            onValueChange = {
                pvm.guardianState.value
                    .updateFirstName(it!!)
            }
        )

        CustomTextField(
            label = stringResource(id = R.string.form_lastname),
            value = lastName,
            onValueChange = {
                pvm.guardianState.value
                    .updateLastName(it!!)
            }
        )

        SelectKinship(
            kinship = kinship,
            updateKinship = pvm::updateKinship
        )

        Spacer(modifier = Modifier.height(10.dp))
    }

    FloatCard {
        Spacer(modifier = Modifier.height(10.dp))

        PhoneTextField(
            phone = phone,
            onValueChange = {
                pvm.guardianState.value
                    .updatePhone(it!!)
            }
        )

        EmailTextField(
            email = email,
            onValueChange = {
                pvm.guardianState.value
                    .updateEmail(it)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

fun isFormInfoValid(patientState: PatientState): Boolean {
    return patientState.firstName.value.isNotBlank() &&
           patientState.lastName.value.isNotBlank() &&
           patientState.birthDate.value.isNotBlank() &&
           patientState.gender.value != null &&
           patientState.ethnicity.value != null
}

fun isFormPerfilValid(patientState: PatientState): Boolean {
    return patientState.familyCases.value != null &&
           patientState.pregnancyComplications.value != null &&
           patientState.premature.value != null
}

fun isFormGuardianValid(guardianState: GuardianState, kinship: Kinship?): Boolean {
    return guardianState.firstName.value.isNotEmpty() &&
           guardianState.lastName.value.isNotEmpty() &&
           guardianState.phone.value.isNotEmpty() &&
           guardianState.email.value.isNotEmpty() &&
           kinship != null
}