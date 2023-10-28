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

val bgColorFirst = Lilas
const val paddingLogoFirst = 96
const val sizeLogoFirst = 170
const val sizeSorridentStarFirst = 180
const val paddingBottomFirst = 28
val textBottomStyleFirst = SofiaTypography.text2.copy(color = BrillantPurple)

@Composable
fun FirstSplashScreen(navController: NavController){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(bgColorFirst)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = paddingLogoFirst.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_logo_sofia),
                contentDescription = "Logo",
                modifier = Modifier.size(sizeLogoFirst.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_sorrident_star),
            contentDescription = "Sorrident Star",
            modifier = Modifier.size(sizeSorridentStarFirst.dp)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = paddingBottomFirst.dp)
        ){
            Text(
                style = textBottomStyleFirst,
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
            SecondSplashScreen(navController = rememberNavController())
        }
    }
}