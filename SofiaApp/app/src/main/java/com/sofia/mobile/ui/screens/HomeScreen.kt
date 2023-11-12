package com.sofia.mobile.ui.screens

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.components.buttons.StarButton
import com.sofia.mobile.ui.components.cards.WelcomeCard
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar


@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            CustomTopAppBar()
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Spacer(modifier = Modifier.height(12.dp))

            WelcomeCard(name = "Amanda", navController = rememberNavController())

            Row(
                modifier = Modifier.  width(312.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                StarButton(
                    text = "Nova Consulta",
                    onClick = { /*TODO*/ },
                    iconId = R.drawable.ic_star_laugh
                )
                StarButton(
                    text = "Listar Pacientes",
                    onClick = { navController.navigate("patientList") },
                    iconId = R.drawable.ic_star_doubt
                )
                StarButton(
                    text = "Cadastrar paciente",
                    onClick = { navController.navigate("patientRegistration") },
                    iconId = R.drawable.ic_star_laugh
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}