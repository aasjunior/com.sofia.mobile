package com.sofia.mobile.ui.components.text

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.sofia.mobile.ui.theme.SoftPurple

@Composable
fun UnderlinedTextWithNavigation(
    text: String,
    color: Color = SoftPurple,
    onClick: () -> Unit
) {
    Text(
        text = text,
        textDecoration = TextDecoration.Underline,
        color = color,
        modifier = Modifier.clickable(onClick = onClick),
        style = link1.copy(color = SoftPurple)
    )
}