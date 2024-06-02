package com.sofia.mobile.ui.view.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.view.components.textstyles.ClickableLinkText
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h2
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1

@Composable
fun WelcomeCard(
    name: String,
    navController: NavController
){
    BaseCard(width = 312.dp, height = 176.dp) {
        Box(
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 15.dp)
                .width(260.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.main_welcome, name),
                        style = h2
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Column(
                        modifier = Modifier.width(160.dp),
                    ){
                        Text(
                            text = stringResource(id = R.string.main_description),
                            style = text1.copy(Color.Black)
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        
                        ClickableLinkText(
                            text = stringResource(id = R.string.link_learnmore),
                            onClick = {
                                navController.navigate(MainNavOptions.LearnMoreScreen.name)
                            }
                        )
                    }
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(id = R.drawable.ic_illustration),
                        contentDescription = "Welcome Card"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun WelcomeCardPreview(){
    WelcomeCard(name = "Amanda", navController = rememberNavController())
}