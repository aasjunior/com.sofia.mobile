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
import androidx.compose.material3.Text
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.models.PacienteModel
import com.sofia.mobile.ui.components.inputs.RoundCheckbox
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.theme.Gray1
import com.sofia.mobile.ui.theme.Gray3
import com.sofia.mobile.ui.theme.Lilas
import com.sofia.mobile.ui.viewmodels.PatientListViewModel

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
    val grouped = patients.groupBy { it.nome[0].uppercase()[0] }
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
                                    text = patient.nome,
                                    style = body1
                                )
                            }
                            // Add a divider for all but the last item
                            if(index < patientsForInitial.size - 1) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    HorizontalDivider(
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
    viewModel: PatientListViewModel
) {
    val patients = viewModel.patients.value
    val grouped = patients.groupBy { it.nome[0].uppercase()[0] }
    val listState = rememberLazyListState()
    val allChecked = remember { mutableStateOf(false) }

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
                        viewModel.selectAllPatients(checked)
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
                        Column{
                            Row(
                                modifier = Modifier.padding(horizontal = 18.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                RoundCheckbox(
                                    checked = patient.isSelected,
                                    onCheckedChange = { checked ->
                                        viewModel.selectPatient(patient, checked)
                                        if(!checked){
                                            allChecked.value = false
                                        }else{
                                            allChecked.value = patients.all { it.isSelected }
                                        }
                                    },
                                )
                                Text(
                                    text = patient.nome,
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