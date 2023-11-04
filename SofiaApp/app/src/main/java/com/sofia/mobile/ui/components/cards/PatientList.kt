package com.sofia.mobile.ui.components.cards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.models.Etnia
import com.sofia.mobile.models.Paciente
import com.sofia.mobile.models.Sexo
import com.sofia.mobile.ui.components.inputs.RoundCheckbox
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.theme.Gray1
import com.sofia.mobile.ui.theme.Gray3
import java.time.LocalDate

@Composable
fun CharacterHeader(character: Char) {
    Text(
        modifier = Modifier.padding(horizontal = 48.dp, vertical = 8.dp),
        text = character.toString().uppercase(),
        style = body1.copy(color = Gray1)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PatientList(patients: List<Paciente>) {
    val grouped = patients.groupBy { it.getNome()[0].uppercase()[0] }
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        grouped.forEach { (initial, patientsForInitial) ->
            stickyHeader{
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
                        patientsForInitial.forEachIndexed {index, patient ->
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
                                    //modifier = Modifier.padding(start = 20.dp),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PatientCheckList(patients: List<Paciente>) {
    val grouped = patients.groupBy { it.getNome()[0].uppercase()[0] }
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        grouped.forEach { (initial, patientsForInitial) ->
            stickyHeader{
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
                        patientsForInitial.forEachIndexed {index, patient ->
                            val isChecked = remember { mutableStateOf(false) }
                            Row(
                                modifier = Modifier.padding(horizontal = 18.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                RoundCheckbox(
                                    checked = isChecked.value,
                                    onCheckedChange = { isChecked.value = it },
                                )
                                Text(
                                    //modifier = Modifier.padding(start = 20.dp),
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

/*
@Preview
@Composable
fun PatientListPreview() {
    val paciente1 = Paciente("Pedro Rodriguez", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.MASCULINO, false, false, false)
    val paciente2 = Paciente("Maria Silva", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)
    val paciente3 = Paciente("Paula Lima dos Santo", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)

    val patients: List<Paciente> = listOf(paciente1, paciente2, paciente3).sortedBy { it.getNome() }

    PatientList(patients)
}
*/

@Preview
@Composable
fun PatientCheckListPreview() {
    val paciente1 = Paciente("Pedro Rodriguez", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.MASCULINO, false, false, false)
    val paciente2 = Paciente("Maria Silva", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)
    val paciente3 = Paciente("Paula Lima dos Santo", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)

    val patients: List<Paciente> = listOf(paciente1, paciente2, paciente3).sortedBy { it.getNome() }

    PatientCheckList(patients)
}
