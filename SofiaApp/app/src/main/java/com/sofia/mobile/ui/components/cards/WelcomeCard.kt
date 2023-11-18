package com.sofia.mobile.ui.components.cards

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.components.text.UnderlinedTextNavigation
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.h2
import com.sofia.mobile.ui.theme.Black
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.White

@Composable
fun WelcomeCard(
    name: String,
    navController: NavController
){
    ElevatedCard(
        modifier = Modifier
            .width(312.dp)
            .height(176.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = White,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(1.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 15.dp)
                .width(260.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                ){
                    Text(
                        text = "Olá, $name!",
                        style = h2.copy(color = BrillantPurple)
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
                            text = stringResource(id = R.string.welcome_card_text),
                            style = body1.copy(color = Black)
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        UnderlinedTextNavigation(text = "Saiba mais", onClick = { navController.navigate("destination_screen") })
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
fun WelcomeCardPreview(){
    WelcomeCard(name = "Amanda", navController = rememberNavController())
}