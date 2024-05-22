package com.sofia.mobile.ui.view.components.cards

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BaseCard(
    width: Dp,
    height: Dp,
    roudedCornerShape: Dp = 10.dp,
    cardElevation: Dp = 1.dp,
    content: @Composable () -> Unit
){
    ElevatedCard(
        modifier = Modifier
            .width(width)
            .height(height),
        shape = RoundedCornerShape(roudedCornerShape),
        colors = CardDefaults.elevatedCardColors(
            containerColor = White,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(cardElevation),
    ){
        content()
    }
}