package com.sofia.mobile.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.Black
import com.sofia.mobile.ui.theme.SofiaTypography

@Composable
fun StarButton(
    text: String,
    onClick: () -> Unit,
    iconId: Int
){
  Surface(
      onClick = onClick,
      shape = MaterialTheme.shapes.medium,
      color = Color.Transparent,
      modifier = Modifier.width(90.dp)
  ) {
      Column(
          horizontalAlignment = Alignment.CenterHorizontally
      ){
          Image(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(90.dp),
              painter = painterResource(id = iconId),
              contentDescription = "Star Button Icon"
          )
          Text(
              text = text,
              style = SofiaTypography.text2.copy(color = Black),
              textAlign = TextAlign.Center
          )
      }
  }  
}

@Preview
@Composable
private fun StarButtonPreview(){
    StarButton(text = "Nova Consulta", onClick = { /*TODO*/ }, iconId = R.drawable.ic_star_laugh)
}