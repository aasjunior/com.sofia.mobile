package com.sofia.mobile.ui.view.contents.appdrawer

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class AppDrawerItemInfo<T>(
    val drawerOption: T,
    @StringRes val title: Int,
    val drawableId: ImageVector,
    @StringRes val descriptionId: Int
)