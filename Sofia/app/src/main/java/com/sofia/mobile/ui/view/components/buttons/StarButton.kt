package com.sofia.mobile.ui.view.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text2

@Composable
fun StarButton(
    text: String,
    onClick: () -> Unit,
    iconId: Int
){
    Surface(
        onClick = {onClick()},
        //shape = MaterialTheme.shapes.medium,
        color = Color.Transparent,
        modifier = Modifier.width(IntrinsicSize.Min)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                modifier = Modifier
                    .size(90.dp),
                painter = painterResource(id = iconId),
                contentDescription = "Star Button Icon"
            )
            Text(
                text = text,
                style = text2.copy(color = Black),
                textAlign = TextAlign.Center,
                modifier = Modifier.width(IntrinsicSize.Min),
                maxLines = 2
            )
        }
    }
}

@Preview
@Composable
private fun StarButtonPreview(){
    StarButton(text = "Nova Consulta", onClick = { /*TODO*/ }, iconId = R.drawable.ic_star_laugh)
}