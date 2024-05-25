package com.sofia.mobile.ui.view.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1

@Composable
fun CustomOptionsCard(
    options: List<Pair<String, () -> Unit>>,
    backgroundColor: Color = Lilas
) {
    Card(
        modifier = Modifier
            .widthIn(min = 120.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(backgroundColor)

    ) {
        Column(
            modifier = Modifier
                .widthIn(min = 120.dp)
                .padding(vertical = 6.dp)
        ){
            options.forEach { (label, action) ->
                ClickableText(
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .padding(horizontal = 18.dp, vertical = 6.dp),
                    text = AnnotatedString(label),
                    onClick = { action.invoke() },
                    style = text1.copy(color = BrillantPurple)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomOptionsCardPreview(){
    CustomOptionsCard(
        options = listOf(
            "Novo" to { /* Faça algo quando o primeiro botão for clicado */ },
            "Deletar" to { /* Faça algo quando o segundo botão for clicado */ }
        )
    )
}