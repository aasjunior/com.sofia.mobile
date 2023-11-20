package com.sofia.mobile.ui.components.forms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sofia.mobile.R
import com.sofia.mobile.ui.components.text.body2
import com.sofia.mobile.ui.components.text.fs12
import com.sofia.mobile.ui.theme.Gray1
import com.sofia.mobile.ui.theme.White

@Composable
fun FormProgress(currentStep: Int) {
    ElevatedCard(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(4.dp)
                .background(White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when(currentStep){
                0 -> FormProgressStep1()
                1 -> FormProgressStep2()
                2 -> FormProgressStep3()
            }
        }
    }
}

@Preview
@Composable
private fun FormProgressPreview(){
    FormProgress(2)
}

@Composable
fun FormProgressStep1(){
    Row(
        modifier = Modifier.width(260.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_step1),
                    contentDescription = null
                )
                Text(
                    text = "Informações",
                    style = fs12.copy(Gray1)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(50.dp)
                    .zIndex(1f)
                    .offset(x = 45.dp, y = -10.dp)
            )
        }
        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.size(25.dp),
                    text = "2",
                    textAlign = TextAlign.Center,
                    style = body2.copy(Gray1)
                )
                Text(
                    text = "Perfil",
                    style = fs12.copy(Gray1)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(50.dp)
                    .zIndex(1f)
                    .offset(x = 45.dp, y = -10.dp)
            )
        }

        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.size(25.dp),
                    text = "3",
                    textAlign = TextAlign.Center,
                    style = body2.copy(Gray1)
                )
                Text(
                    text = "Responsável",
                    style = fs12.copy(Gray1)
                )
            }
        }
    }
}

@Composable
fun FormProgressStep2(){
    Row(
        modifier = Modifier.width(260.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_step_check),
                    contentDescription = null
                )
                Text(
                    text = "Informações",
                    style = fs12.copy(Gray1)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(50.dp)
                    .zIndex(1f)
                    .offset(x = 45.dp, y = -10.dp)
            )
        }
        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_step2),
                    contentDescription = null
                )
                Text(
                    text = "Perfil",
                    style = fs12.copy(Gray1)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(50.dp)
                    .zIndex(1f)
                    .offset(x = 45.dp, y = -10.dp)
            )
        }

        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.size(25.dp),
                    text = "3",
                    textAlign = TextAlign.Center,
                    style = body2.copy(Gray1)
                )
                Text(
                    text = "Responsável",
                    style = fs12.copy(Gray1)
                )
            }
        }
    }
}

@Composable
fun FormProgressStep3(){
    Row(
        modifier = Modifier.width(260.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_step_check),
                    contentDescription = null
                )
                Text(
                    text = "Informações",
                    style = fs12.copy(Gray1)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(50.dp)
                    .zIndex(1f)
                    .offset(x = 45.dp, y = -10.dp)
            )
        }
        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_step_check),
                    contentDescription = null
                )
                Text(
                    text = "Perfil",
                    style = fs12.copy(Gray1)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(50.dp)
                    .zIndex(1f)
                    .offset(x = 45.dp, y = -10.dp)
            )
        }

        Box {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_step3),
                    contentDescription = null
                )
                Text(
                    text = "Responsável",
                    style = fs12.copy(Gray1)
                )
            }
        }
    }
}


