package com.sofia.mobile.ui.view.contents.appdrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions

object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            MainNavOptions.HomeScreen,
            R.string.drawer_home,
            Icons.Filled.Home,
            R.string.drawer_home_description
        )
    )
}