package com.sofia.mobile.ui.components.forms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
            .padding(8.dp)
            .height(80.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            when(currentStep){
                0 -> FormProgressStep1()
                1 -> FormProgressStep2()
                2 -> FormProgressStep3()
            }
        }
    }
}

@Composable
fun FormProgressStep1(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.zIndex(2f)) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step1),
            contentDescription = null
        )
        Text(text = "Informações", style = fs12.copy(Gray1))
    }
    HorizontalDivider(modifier = Modifier.fillMaxWidth(0.25f).zIndex(1f))
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.zIndex(2f)) {
        Text(text = "2", style = body2.copy(Gray1))
        Text(text = "Perfil", style = fs12.copy(Gray1))
    }
    HorizontalDivider(modifier = Modifier.fillMaxWidth(0.25f).zIndex(1f))
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.zIndex(2f)) {
        Text(text = "3", style = body2.copy(Gray1))
        Text(text = "Responsável", style = fs12.copy(Gray1))
    }
}

@Composable
fun FormProgressStep2(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step_check),
            contentDescription = null
        )
        Text(text = "Informações", style = fs12.copy(Gray1))
    }
    HorizontalDivider(modifier = Modifier.fillMaxWidth(0.25f))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step2),
            contentDescription = null
        )
        Text(text = "Perfil", style = fs12.copy(Gray1))
    }
    HorizontalDivider(modifier = Modifier.fillMaxWidth(0.25f))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "3", style = body2.copy(Gray1))
        Text(text = "Responsável", style = fs12.copy(Gray1))
    }
}

@Composable
fun FormProgressStep3(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.zIndex(1f)) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step_check),
            contentDescription = null
        )
        Text(text = "Informações", style = fs12.copy(Gray1))
    }
    HorizontalDivider(modifier = Modifier.fillMaxWidth(0.25f).zIndex(2f))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step_check),
            contentDescription = null
        )
        Text(text = "Perfil", style = fs12.copy(Gray1))
    }
    HorizontalDivider(modifier = Modifier.fillMaxWidth(0.25f))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.ic_step3),
            contentDescription = null
        )
        Text(text = "Responsável", style = fs12.copy(Gray1))
    }
}