package com.sofia.mobile.ui.view.screens.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.IntroNavOptions
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import com.sofia.mobile.ui.view.components.Copyright
import com.sofia.mobile.ui.view.components.Logo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
){
    val showSecondBox = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit){
        delay(2000)
        showSecondBox.value = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Style.bgColor)
    ){
        if(showSecondBox.value){
            SecondStage()
            LaunchedEffect(Unit){
                delay(2000)
                navController.navigate(IntroNavOptions.LoginScreen.name)
            }
        }else{
            FirstStage(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = Style.paddingLogo)
            )
        }
        
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Style.paddingBottom)
        ){
            Copyright()
        }
    }
}

@Composable
private fun FirstStage(modifier: Modifier){
    Row(modifier = modifier){
        Logo(modifier = Modifier.size(Style.sizeFirstLogo))
    }
    Image(
        painter = painterResource(id = R.drawable.ic_sorrident_star),
        contentDescription = "Sorrident Star",
        modifier = Modifier.size(Style.sizeSorridentStar)
    )
}

@Composable
private fun SecondStage(){
    Logo(
        modifier = Modifier.size(Style.sizeSecondLogo),
        logo = R.drawable.ic_sorrident_star
    )
}

private object Style{
    val bgColor = Lilas
    val spacerValue = 16.dp
    val paddingLogo = 96.dp
    val sizeFirstLogo = 170.dp
    val sizeSecondLogo = 256.dp
    val sizeSorridentStar = 180.dp
    val paddingBottom = 28.dp
}