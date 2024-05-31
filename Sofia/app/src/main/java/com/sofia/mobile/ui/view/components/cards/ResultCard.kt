package com.sofia.mobile.ui.view.components.cards

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles

@Composable
fun ResultCard(result: Boolean){
    val resources = if(result){
        ResultResources(
            R.string.result_positive,
            R.string.result_negative_description,
            R.string.result_negative_description_em,
            R.drawable.ic_star_signs_asd
        )
    }else{
        ResultResources(
            R.string.result_negative,
            R.string.result_negative_description,
            R.string.result_negative_description_em,
            R.drawable.ic_sorrident_star_elipse
        )
    }
    FloatCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    modifier = Modifier.size(105.dp),
                    painter = painterResource(id = resources.resultImageId),
                    contentDescription = null
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = stringResource(id = resources.resultTextId),
                        style = SofiaTextStyles.h2
                    )
                }
            }
            Row(modifier = Modifier.padding(top = 16.dp)){
                CustomTextDescription(resources)
            }
        }
    }
}

@Composable
private fun CustomTextDescription(resources: ResultResources) {
    val descriptionTemplate = stringResource(id = resources.resultDescriptionId)
    val emText = stringResource(id = resources.resultDescriptionEmId)
    val annotatedString = buildAnnotatedString {
        val beforeEmText = descriptionTemplate.substringBefore("%s")
        val afterEmText = descriptionTemplate.substringAfter("%s")

        append(beforeEmText)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(emText)
        }
        append(afterEmText)
    }

    Text(
        text = annotatedString,
        style = SofiaTextStyles.phrase
    )
}

private data class ResultResources(
    @StringRes val resultTextId: Int,
    @StringRes val resultDescriptionId: Int,
    @StringRes val resultDescriptionEmId: Int,
    @DrawableRes val resultImageId: Int,
)