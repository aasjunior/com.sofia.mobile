package com.sofia.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sofia.mobile.R
import com.sofia.mobile.models.Etnia
import com.sofia.mobile.models.Paciente
import com.sofia.mobile.models.Sexo
import com.sofia.mobile.ui.components.buttons.FloatingAddButton
import com.sofia.mobile.ui.components.cards.PatientCheckList
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Gray1
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientListScreen(
    nPatient: Int
){
    val paciente1 = Paciente("Pedro Rodriguez", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.MASCULINO, false, false, false)
    val paciente2 = Paciente("Maria Silva", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)
    val paciente3 = Paciente("Paula Lima dos Santo", LocalDate.of(1990, 4, 15), Etnia.NEGRA, Sexo.FEMININO, false, false, false)

    val patients: List<Paciente> = listOf(paciente1, paciente2, paciente3).sortedBy { it.getNome() }

    Scaffold(
        topBar = {
            CustomTopAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ){
                Text(
                    text = stringResource(id = R.string.patient_list_header),
                    style = h3.copy(color = BrillantPurple)
                )

                Text(
                    text = "$nPatient pacientes cadastrados",
                    style = body1.copy(color = Gray1)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.End
            ){
                FloatingAddButton(
                    onClick = {}
                )
            }

            PatientCheckList(patients)

        }
    }
}

@Preview
@Composable
fun PatientListScreenPreview(){
    PatientListScreen(22)
}