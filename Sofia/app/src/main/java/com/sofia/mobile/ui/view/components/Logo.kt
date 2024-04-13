package com.sofia.mobile.ui.view.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.sofia.mobile.R

@Composable
fun Logo(
    @DrawableRes logo: Int = R.drawable.ic_logo_sofia,
    modifier: Modifier
){
    Image(
        painter = painterResource(id = logo),
        contentDescription = "Logo",
        modifier = modifier
    )
}