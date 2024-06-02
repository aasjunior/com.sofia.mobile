package com.sofia.mobile.ui.view.contents.appdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.navigation.routes.NavRoutes
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1
import com.sofia.mobile.ui.viewmodel.LoginViewModel
import com.sofia.mobile.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun <T: Enum<T>> AppDrawerContent(
    drawerState: DrawerState,
    menuItems: List<AppDrawerItemInfo<T>>,
    defaultPick: T,
    navHostController: NavHostController,
    routeMain: MainNavOptions? = null,
    onClick: (String) -> Unit
){
    var currentPick by remember { mutableStateOf(defaultPick) }
    val coroutineScope= rememberCoroutineScope()

    ModalDrawerSheet(drawerContainerColor = Lilas){
        Surface(
            color = Lilas
        ){
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item {
                        UserMenu(navHostController)
                    }
                    items(menuItems) { item ->
                        AppDrawerItem(
                            item = item,
                            navController = navHostController,
                            routeMain = routeMain
                        ){ navOption ->
                            if(currentPick == navOption){
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                return@AppDrawerItem
                            }

                            currentPick = navOption
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            onClick(navOption.name)
                        }
                    }
                }
                Logout(
                    navController = navHostController,
                    drawerState = drawerState
                )
            }
        }
    }
}

@Composable
private fun UserMenu(navHostController: NavHostController){
    val uvm: UserViewModel = viewModel()
    uvm.getUser()
    val user by uvm.user.collectAsState(null)

    Row(
        modifier = Modifier
            .width(200.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(57.dp),
            painter = painterResource(id = R.drawable.ic_cute_icon),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = user?.firstName ?: "None",
                style = h3.copy(Color.Black)
            )
            Text(
                text = stringResource(id = R.string.drawer_perfil),
                style = text1.copy(Color.Black)
            )
        }
    }
}

@Composable
private fun Logout(
    navController: NavHostController,
    drawerState: DrawerState
){

    val lvm: LoginViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .clickable {
                lvm.logout()
                coroutineScope.launch {
                    drawerState.close()
                }
                navController.navigate(NavRoutes.IntroRoute.name) {
                    popUpTo(NavRoutes.IntroRoute.name) {
                        inclusive = true
                    }
                }
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
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = "Logout",
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = stringResource(id = R.string.drawer_logout),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}