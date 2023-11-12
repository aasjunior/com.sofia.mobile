package com.sofia.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.components.buttons.StarButton
import com.sofia.mobile.ui.components.cards.WelcomeCard
import com.sofia.mobile.ui.components.forms.PatientForm
import com.sofia.mobile.ui.components.navbar.appbar.CustomTopAppBar
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple

@Composable
fun PatientRegistrationScreen(navController: NavController){
    Scaffold(
        topBar = {
            CustomTopAppBar()
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.patient_registration_header),
                style = h3.copy(color = BrillantPurple)
            )
            PatientForm(navController)
        }
    }
}

@Preview
@Composable
fun PatientRegistrationScreenPreview(){
    PatientRegistrationScreen(navController = rememberNavController())
}