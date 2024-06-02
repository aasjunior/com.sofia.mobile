package com.sofia.mobile.ui.view.screens.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme.Gray1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1

@Composable
fun LoadingScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            modifier = Modifier
                .size(280.dp),
            painter = painterResource(id = R.drawable.ic_sorrident_star_splash_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(id = R.string.processing),
            style = h1
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.processing_msg),
            style = text1.copy(Color.Black)
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            modifier = Modifier.padding(horizontal = 50.dp, vertical = 14.dp),
            colors = ButtonDefaults.outlinedButtonColors(Gray1),
            onClick = {
                navController.navigate(MainNavOptions.CheckListScreen.name)
            }
        ) {
            Text(
                text = stringResource(id = R.string.btn_cancel),
                style = text1
            )
        }
    }
}