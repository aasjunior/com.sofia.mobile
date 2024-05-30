package com.sofia.mobile.ui.view.screens.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.buttons.CustomOutlinedButton
import com.sofia.mobile.ui.view.components.cards.FloatCard
import com.sofia.mobile.ui.view.components.textstyles.ClickableLinkText
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h2
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.phrase
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text3
import com.sofia.mobile.ui.view.contents.containers.BaseContent

@Composable
fun CheckListResultScreen(navController: NavController, result: Boolean){
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

    Spacer(modifier = Modifier.height(16.dp))

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            FloatCard {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 25.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(BrillantPurple),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Checked",
                            tint = Color.White
                        )

                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.result_title),
                        style = text3.copy(BrillantPurple)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
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
                                style = h2
                            )
                        }
                    }
                    Row(modifier = Modifier.padding(top = 16.dp)){
                        CustomTextDescription(resources)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            ClickableLinkText(text = stringResource(id = R.string.result_view_all_data)){

            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(15.dp)
        ){
            CustomOutlinedButton(
                text = stringResource(id = R.string.btn_back),
                onClick = { /*TODO*/ }
            )
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
        style = phrase
    )
}

private data class ResultResources(
    @StringRes val resultTextId: Int,
    @StringRes val resultDescriptionId: Int,
    @StringRes val resultDescriptionEmId: Int,
    @DrawableRes val resultImageId: Int,
)

@Preview
@Composable
fun PreviewCheckListResultScreen() {
    BaseContent(navController = rememberNavController(), drawerState = rememberDrawerState(
        DrawerValue.Closed)
    ) {
        CheckListResultScreen(navController = rememberNavController(), result = false)
    }
}