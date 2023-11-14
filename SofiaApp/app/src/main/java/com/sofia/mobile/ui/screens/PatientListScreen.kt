package com.sofia.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.models.PacienteModel
import com.sofia.mobile.domain.Sexo
import com.sofia.mobile.ui.components.buttons.CustomButton
import com.sofia.mobile.ui.components.buttons.FloatingAddButton
import com.sofia.mobile.ui.components.cards.CustomOptionsCard
import com.sofia.mobile.ui.components.cards.PatientCheckList
import com.sofia.mobile.ui.components.cards.PatientList
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Gray1
import com.sofia.mobile.ui.theme.SoftPurple
import java.time.LocalDate

@Composable
fun PatientListScreen(
    navController: NavController,
    nPatient: Int
){
    val pacienteModel1 = PacienteModel("Pedro Rodriguez", LocalDate.of(1990, 4, 15), Etnia.PARDA, Sexo.MASCULINO, false, false, false)
    val pacienteModel2 = PacienteModel("Maria Silva", LocalDate.of(1990, 4, 15), Etnia.PRETA, Sexo.FEMININO, false, false, false)
    val pacienteModel3 = PacienteModel("Paula Lima dos Santo", LocalDate.of(1990, 4, 15), Etnia.BRANCA, Sexo.FEMININO, false, false, false)

    val patients: List<PacienteModel> = listOf(pacienteModel1, pacienteModel2, pacienteModel3).sortedBy { it.getNome() }

    val isCardOpen = remember { mutableStateOf(false) }
    val isDeleteMode = remember { mutableStateOf(false) }
    val totalChecked = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CustomTopAppBar()
        },
        bottomBar = {
            if(isDeleteMode.value){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    CustomButton(
                        text = "Deletar",
                        onClick = {}
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ){
                if(isDeleteMode.value){
                    Text(
                        text = "${totalChecked.value} selecionado(s)",
                        style = h3.copy(color = BrillantPurple)
                    )
                }else{
                    Text(
                        text = stringResource(id = R.string.patient_list_header),
                        style = h3.copy(color = BrillantPurple)
                    )

                    Text(
                        text = "$nPatient pacientes cadastrados",
                        style = body1.copy(color = Gray1)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                if(isCardOpen.value){
                    CustomOptionsCard(
                        options = listOf(
                            "Novo" to { navController.navigate("patientRegistration") },
                            "Deletar" to { isDeleteMode.value = !isDeleteMode.value }
                        )
                    )
                }else{
                    FloatingAddButton(
                        onClick = { navController.navigate("patientRegistration") }
                    )
                }

                IconButton(onClick = { isCardOpen.value = !isCardOpen.value }){
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                        tint = SoftPurple
                    )
                }
            }

            if(isDeleteMode.value) {
                PatientCheckList(patients, totalChecked)
            }else{
                PatientList(patients)
            }
        }
    }
}

@Preview
@Composable
fun PatientListScreenPreview(){
    PatientListScreen(navController = rememberNavController(), 3)
}