package com.sofia.mobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Lilas
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.theme.SofiaTypography

val bgColorSecond = Lilas
val sizeLogoSecond = 256
val paddingBottomSecond = 28
val textBottomStyleSecond = SofiaTypography.text2.copy(color = BrillantPurple)

@Composable
fun SecondSplashScreen(navController: NavController){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(bgColorSecond)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_sofia),
            contentDescription = "Sorrident Star",
            modifier = Modifier.size(sizeLogoSecond.dp)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = paddingBottomSecond.dp)
        ){
            Text(
                style = textBottomStyleSecond,
                text = "By AJA Group",
            )
        }

    }
}

@Preview
@Composable
fun SecondSplashScreenPreview(){
    SofiaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SecondSplashScreen(navController = rememberNavController())
        }
    }
}