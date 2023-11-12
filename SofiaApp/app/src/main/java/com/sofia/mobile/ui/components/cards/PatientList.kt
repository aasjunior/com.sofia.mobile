package com.sofia.mobile.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.models.PacienteModel
import com.sofia.mobile.domain.Sexo
import com.sofia.mobile.ui.components.inputs.RoundCheckbox
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.theme.Gray1
import com.sofia.mobile.ui.theme.Gray3
import com.sofia.mobile.ui.theme.Lilas
import java.time.LocalDate

@Composable
fun CharacterHeader(character: Char) {
    Text(
        modifier = Modifier.padding(horizontal = 48.dp, vertical = 8.dp),
        text = character.toString().uppercase(),
        style = body1.copy(color = Gray1)
    )
}

@Composable
fun PatientList(patients: List<PacienteModel>) {
    val grouped = patients.groupBy { it.getNome()[0].uppercase()[0] }
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        grouped.forEach { (initial, patientsForInitial) ->
            item {
                CharacterHeader(initial)
            }
            item {
                ElevatedCard(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column{
                        patientsForInitial.forEachIndexed { index, patient ->
                            Row(
                                modifier = Modifier.padding(horizontal = 18.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                Image(
                                    modifier = Modifier.size(32.dp),
                                    painter = painterResource(id = R.drawable.ic_cute_star),
                                    contentDescription = "Cute Star"
                                )
                                Text(
                                    text = patient.getNome(),
                                    style = body1
                                )
                            }
                            // Add a divider for all but the last item
                            if(index < patientsForInitial.size - 1) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Divider(
                                        modifier = Modifier.fillMaxWidth(0.8f),
                                        color = Gray3,
                                        thickness = 1.dp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PatientCheckList(
    patients: List<PacienteModel>,
    totalChecked: MutableState<Int>,
) {
    val grouped = patients.groupBy { it.getNome()[0].uppercase()[0] }
    val listState = rememberLazyListState()
    val allChecked = remember { mutableStateOf(false) }
    val patientCheckedStates = remember { patients.associate { it.getNome() to mutableStateOf(false) } }

    LazyColumn(state = listState) {
        item {
            Row(
                modifier = Modifier.padding(horizontal = 41.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                RoundCheckbox(
                    modifier = Modifier.size(20.dp),
                    checked = allChecked.value,
                    onCheckedChange = { checked ->
                        allChecked.value = checked
                        patientCheckedStates.values.forEach { it.value = checked }
                        totalChecked.value = if (checked) patients.size else 0
                    }
                )
                Text(
                    text = "Todos",
                    style = body1.copy(color = Lilas)
                )
            }
        }
        grouped.forEach { (initial, patientsForInitial) ->
            item {
                CharacterHeader(initial)
            }
            item {
                ElevatedCard(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    patientsForInitial.forEach { patient ->
                        val isChecked = patientCheckedStates[patient.getNome()]!!

                        Column{
                            Row(
                                modifier = Modifier.padding(horizontal = 18.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                RoundCheckbox(
                                    checked = isChecked.value,
                                    onCheckedChange = { checked ->
                                        isChecked.value = checked
                                        totalChecked.value += if (checked) 1 else -1
                                        allChecked.value = totalChecked.value == patients.size
                                    },
                                )
                                Text(
                                    text = patient.getNome(),
                                    style = body1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PatientListPreview() {
    val pacienteModel1 = PacienteModel("Pedro Rodriguez", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.MASCULINO, false, false, false)
    val pacienteModel2 = PacienteModel("Maria Silva", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)
    val pacienteModel3 = PacienteModel("Paula Lima dos Santo", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)

    val patients: List<PacienteModel> = listOf(pacienteModel1, pacienteModel2, pacienteModel3).sortedBy { it.getNome() }

    PatientList(patients)
}

@Preview
@Composable
fun PatientCheckListPreview() {
    val pacienteModel1 = PacienteModel("Pedro Rodriguez", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.MASCULINO, false, false, false)
    val pacienteModel2 = PacienteModel("Maria Silva", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)
    val pacienteModel3 = PacienteModel("Paula Lima dos Santo", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)

    val patients: List<PacienteModel> = listOf(pacienteModel1, pacienteModel2, pacienteModel3).sortedBy { it.getNome() }

    val totalChecked = remember { mutableStateOf(0) }
    PatientCheckList(patients, totalChecked)
}
