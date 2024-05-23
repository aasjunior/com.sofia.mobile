package com.sofia.mobile.ui.view.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.Gray3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text2

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = BrillantPurple,
    contentColor: Color = Gray3,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = Modifier
            .width(154.dp)
            .padding(16.dp)
    ) {
        Text(
            text = text,
            style = text2
        )
    }
}

@Composable
fun CustomOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        border = BorderStroke(1.dp, BrillantPurple),
        modifier = Modifier
            .width(154.dp)
            .padding(16.dp)
    ) {
        Text(
            text = text,
            style = text2.copy(color = BrillantPurple)
        )
    }
}