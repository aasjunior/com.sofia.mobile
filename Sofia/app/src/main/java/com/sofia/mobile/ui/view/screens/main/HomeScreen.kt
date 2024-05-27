package com.sofia.mobile.ui.view.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaColorScheme.SoftLilas
import com.sofia.mobile.ui.view.components.buttons.StarButton
import com.sofia.mobile.ui.view.components.cards.WelcomeCard
import com.sofia.mobile.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftLilas),
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
                text = stringResource(id = R.string.btn_new_consultation),
                onClick = { navController.navigate(MainNavOptions.CheckListScreen.name) },
                iconId = R.drawable.ic_star_laugh
            )
            StarButton(
                text = stringResource(id = R.string.btn_list_patient),
                onClick = { navController.navigate(MainNavOptions.PatientListScreen.name) },
                iconId = R.drawable.ic_star_doubt
            )
            StarButton(
                text = stringResource(id = R.string.btn_register_patient),
                onClick = { navController.navigate(MainNavOptions.PatientRegisterScreen.name) },
                iconId = R.drawable.ic_star_laugh
            )
        }
    }
}

@Composable
fun ButtonLogout(navController: NavController, drawerState: DrawerState){
    val lvm: LoginViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    Button(
        onClick = {
            lvm.logout()
            coroutineScope.launch{
                drawerState.close()
            }
            navController.navigate(NavRoutes.IntroRoute.name){
                popUpTo(NavRoutes.IntroRoute.name){
                    inclusive = true
                }
            }
        }
    ) {
        Text(text = "logout")
    }
}

@Preview
@Composable
private fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}