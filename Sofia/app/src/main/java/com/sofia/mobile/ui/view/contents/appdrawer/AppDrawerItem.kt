package com.sofia.mobile.ui.view.contents.appdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme
import com.sofia.mobile.ui.theme.SofiaColorScheme.SoftLilas
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles

@Composable
fun <T> AppDrawerItem(
    item: AppDrawerItemInfo<T>,
    navController: NavController? = null,
    routeMain: MainNavOptions? = null,
    onClick: (options: T) -> Unit
){
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .clickable {
            if(routeMain != null){
                navController!!.navigate(routeMain.name)
            }
            onClick(item.drawerOption)
        },
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ){
            Icon(
                imageVector = item.drawableId,
                contentDescription = stringResource(id = item.descriptionId),
                modifier = Modifier
                    .size(24.dp)
            )
            Text(
                text = stringResource(id = item.title),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = SofiaColorScheme.Gray3,
            thickness = 1.dp
        )
    }
}