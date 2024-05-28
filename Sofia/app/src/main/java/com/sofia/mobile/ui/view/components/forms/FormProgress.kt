package com.sofia.mobile.ui.view.components.forms

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
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.Gray1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.legend1
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text2

@Composable
fun FormProgress(
    currentStep: Int,
    labels: List<Int>
) {

    ElevatedCard(
        modifier = Modifier
            .height(80.dp)
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when(currentStep){
                0 -> FormProgressStep(
                    step = 1,
                    labels = labels,
                    icons = listOf(R.drawable.ic_step_check, R.drawable.ic_step2, R.drawable.ic_step3)
                )
                1 -> FormProgressStep(
                    step = 2,
                    labels = labels,
                    icons = listOf(R.drawable.ic_step_check, R.drawable.ic_step_check, R.drawable.ic_step3)
                )
                2 -> FormProgressStep(
                    step = 3,
                    labels = labels,
                    icons = listOf(R.drawable.ic_step_check, R.drawable.ic_step_check, R.drawable.ic_step_check)
                )
            }
        }
    }
}

@Composable
private fun FormProgressStep(
    step: Int,
    labels: List<Int>,
    icons: List<Int>
) {
    Row(
        modifier = Modifier.width(260.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        labels.forEachIndexed { index, label ->
            Box {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (index < step) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = icons[index]),
                            contentDescription = null
                        )
                    } else {
                        Text(
                            modifier = Modifier.size(25.dp),
                            text = (index + 1).toString(),
                            textAlign = TextAlign.Center,
                            style = text2.copy(Gray1)
                        )
                    }
                    Text(
                        text = stringResource(id = label),
                        style = legend1.copy(Gray1)
                    )
                }
                if(index < labels.size - 1){
                    Divider(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(50.dp)
                            .zIndex(1f)
                            .offset(x = 45.dp, y = (-10).dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FormProgressPreview(){
    val labels = listOf(
        R.string.patient_form_step_01,
        R.string.patient_form_step_02,
        R.string.patient_form_step_03
    )
    FormProgress(2, labels)
}