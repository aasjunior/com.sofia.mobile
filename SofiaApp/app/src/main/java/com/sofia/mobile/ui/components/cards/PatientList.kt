package com.sofia.mobile.ui.components.cards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.models.Etnia
import com.sofia.mobile.models.Paciente
import com.sofia.mobile.models.Sexo
import com.sofia.mobile.ui.components.text.body1
import java.time.LocalDate

@Composable
fun CharacterHeader(character: Char) {
    Text(
        text = character.toString().uppercase(),
        modifier = Modifier.padding(8.dp)
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
                        patientsForInitial.forEach { patient ->
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
    val paciente1 = Paciente("Pedro Rodriguez", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.MASCULINO, false, false, false)
    val paciente2 = Paciente("Maria Silva", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)
    val paciente3 = Paciente("Paula Lima dos Santo", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)

    val patients: List<Paciente> = listOf(paciente1, paciente2, paciente3).sortedBy { it.getNome() }

    PatientList(patients)
}
