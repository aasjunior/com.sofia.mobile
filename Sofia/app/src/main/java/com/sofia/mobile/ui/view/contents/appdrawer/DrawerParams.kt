package com.sofia.mobile.ui.view.contents.appdrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import com.sofia.mobile.R
import com.sofia.mobile.ui.navigation.routes.MainNavOptions

object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            MainNavOptions.HomeScreen,
            R.string.drawer_home,
            Icons.Filled.Home,
            R.string.drawer_home_description
        ),
        AppDrawerItemInfo(
            MainNavOptions.SettingsScreen,
            R.string.drawer_settings,
            Icons.Filled.Settings,
            R.string.drawer_settings_description
        )
    )
}