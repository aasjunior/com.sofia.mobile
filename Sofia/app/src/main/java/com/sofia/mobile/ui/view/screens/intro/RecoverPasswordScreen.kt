package com.sofia.mobile.ui.view.screens.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sofia.mobile.ui.theme.SofiaColorScheme
import com.sofia.mobile.ui.view.components.cards.ComingSoon

@Composable
fun RecoverPasswordScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SofiaColorScheme.SoftLilas),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        ComingSoon(navController, isRecoverPassword = true)
    }
}