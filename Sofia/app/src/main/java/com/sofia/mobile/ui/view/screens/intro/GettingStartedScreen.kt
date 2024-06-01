package com.sofia.mobile.ui.view.screens.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme.SoftLilas
import com.sofia.mobile.ui.view.components.Copyright
import com.sofia.mobile.ui.view.components.Logo
import com.sofia.mobile.ui.view.components.buttons.CustomButton
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
import com.sofia.mobile.ui.viewmodel.LoginViewModel

@Composable
fun GettingStartedScreen(
    navController: NavHostController
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(SoftLilas)
    ){
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        ){
            Logo(modifier = Modifier.size(86.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                modifier = Modifier
                    .size(280.dp),
                painter = painterResource(id = R.drawable.ic_sorrident_star_splash),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(id = R.string.begin_title),
                style = h1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.begin_message_01),
                style = text1.copy(Color.Black)
            )
            Text(
                text = stringResource(id = R.string.begin_message_02),
                style = text1.copy(Color.Black)
            )
            CustomButton(text = stringResource(id = R.string.btn_begin)) {
                navController.navigate(MainNavOptions.HomeScreen.name)
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp)
        ){
            Copyright()
        }
    }
}