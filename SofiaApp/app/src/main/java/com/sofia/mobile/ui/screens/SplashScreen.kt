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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Lilas
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.theme.SofiaTypography
import kotlinx.coroutines.delay

val bgColor = Lilas
const val paddingLogo = 96
const val sizeLogo = 170
const val sizeLogoSecond = 256
const val sizeSorridentStar = 180
const val paddingBottom = 28
val textBottomStyle = SofiaTypography.text2.copy(color = BrillantPurple)


@Composable
fun SplashScreen(navController: NavController) {
    val showSecondBox = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000)
        showSecondBox.value = true
    }

    if (showSecondBox.value) {
        SecondSplashScreen()
        LaunchedEffect(Unit) {
            delay(2000)
            navController.navigate("home")
        }
    } else {
        FirstSplashScreen()
    }
}
@Composable
fun FirstSplashScreen(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = paddingLogo.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_logo_sofia),
                contentDescription = "Logo",
                modifier = Modifier.size(sizeLogo.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_sorrident_star),
            contentDescription = "Sorrident Star",
            modifier = Modifier.size(sizeSorridentStar.dp)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = paddingBottom.dp)
        ){
            Text(
                style = textBottomStyle,
                text = "By AJA Group",
            )
        }

    }
}

@Composable
fun SecondSplashScreen(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_sofia),
            contentDescription = "Sorrident Star",
            modifier = Modifier.size(sizeLogoSecond.dp)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = paddingBottom.dp)
        ){
            Text(
                style = textBottomStyle,
                text = "By AJA Group",
            )
        }

    }
}


@Preview
@Composable
fun FirstSplashScreenPreview(){
    SofiaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FirstSplashScreen()
        }
    }
}

@Preview
@Composable
fun SecondSplashScreenPreview(){
    SofiaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SecondSplashScreen()
        }
    }
}