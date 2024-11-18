package com.sofia.mobile.ui.view.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.domain.common.utils.mergeSort
import com.sofia.mobile.domain.model.patient.Patient
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme
import com.sofia.mobile.ui.theme.SofiaColorScheme.Gray3
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import com.sofia.mobile.ui.view.components.forms.inputs.RoundedCheckbox
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
import com.sofia.mobile.ui.viewmodel.PatientListViewModel


@Composable
fun PatientList(
    navController: NavController,
    patients: List<Patient>,
    navRoute: MainNavOptions = MainNavOptions.PatientProfileScreen
){
    val sortedPatients = mergeSort(patients)
    val grouped = sortedPatients.groupBy { it.firstName[0].uppercaseChar() }
    val listState = rememberLazyListState()

    LazyColumn(state = listState){
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
                    Column(
                        modifier = Modifier.background(White)
                    ) {
                        patientsForInitial.forEachIndexed { index, patient ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 18.dp, vertical = 11.dp)
                                    .clickable {
                                        navController.navigate("${navRoute.name}/${patient.id}")
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(20.dp),
                            ){
                                Image(
                                    modifier = Modifier.size(32.dp),
                                    painter = painterResource(id = R.drawable.ic_cute_star),
                                    contentDescription = "Cute Star"
                                )
                                Text(
                                    text = "${patient.firstName} ${patient.lastName}",
                                    style = text1
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
    plvm: PatientListViewModel
) {
    // val patients = plvm.patients.sortedBy { it.firstName }
    // val grouped = patients.groupBy { it.firstName[0].uppercase()[0] }

    val patients = mergeSort(plvm.patients)
    val grouped = patients.groupBy { it.firstName[0].uppercaseChar() }


    val listState = rememberLazyListState()
    val allChecked = remember { mutableStateOf(false) }

    LazyColumn(state = listState) {
        item {
            Row(
                modifier = Modifier.padding(horizontal = 41.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                RoundedCheckbox(
                    modifier = Modifier.size(20.dp),
                    checked = allChecked.value,
                    onCheckedChange = { checked ->
                        allChecked.value = checked
                        plvm.selectAllPatients(checked)
                    }
                )
                Text(
                    text = "Todos",
                    style = text1.copy(color = Lilas)
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
                        val oncheckedChange = { checked: Boolean ->
                            plvm.selectPatient(patient, checked)
                            if(!checked){
                                allChecked.value = false
                            }else{
                                allChecked.value = patients.all { it.isSelected }
                            }
                        }
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .background(White)
                            .clickable { oncheckedChange(!patient.isSelected) }
                        ){
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 18.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                RoundedCheckbox(
                                    checked = patient.isSelected,
                                    onCheckedChange = oncheckedChange,
                                )
                                Text(
                                    text = "${patient.firstName} ${patient.lastName}",
                                    style = text1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterHeader(character: Char) {
    Text(
        modifier = Modifier.padding(horizontal = 48.dp, vertical = 8.dp),
        text = character.toString().uppercase(),
        style = text1.copy(color = SofiaColorScheme.Gray1)
    )
}