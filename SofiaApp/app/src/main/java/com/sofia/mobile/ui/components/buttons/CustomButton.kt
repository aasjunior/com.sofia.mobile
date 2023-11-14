package com.sofia.mobile.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.components.text.body2
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.Gray3

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
        shape = RoundedCornerShape(50), // Para tornar o botão arredondado
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
            style = body2.copy(color = Gray3)
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
        shape = RoundedCornerShape(50), // Para tornar o botão arredondado
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
            style = body2.copy(color = BrillantPurple)
        )
    }
}



@Preview
@Composable
fun CustomButtonPreview(){
    CustomButton(text = "Deletar", onClick = {})
}

@Preview
@Composable
fun CustomOutlinedButtonPreview(){
    CustomOutlinedButton(text = "Voltar", onClick = {})
}