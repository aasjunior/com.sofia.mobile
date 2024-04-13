package com.sofia.mobile.ui.view.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sofia.mobile.R
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles

@Composable
fun Copyright(){
    Text(
        text = stringResource(id = R.string.app_copyright),
        style = SofiaTextStyles.copyright
    )
}