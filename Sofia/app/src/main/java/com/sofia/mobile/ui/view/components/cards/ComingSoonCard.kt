package com.sofia.mobile.ui.view.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles

@Composable
fun ComingSoon(navController: NavController){
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
                    painter = painterResource(id = R.drawable.ic_star_signs_asd),
                    contentDescription = null
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = stringResource(id = R.string.coming_soon),
                        style = SofiaTextStyles.h3
                    )
                }
            }
            Row(modifier = Modifier.padding(top = 20.dp)){
                Text(
                    text = stringResource(id = R.string.coming_soon_msg),
                    style = SofiaTextStyles.text1
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(onClick = { navController.navigate(MainNavOptions.HomeScreen.name) }) {
                    Text(text = stringResource(id = R.string.btn_back))
                }
            }
        }
    }
}