package com.sofia.mobile.ui.view.components.textstyles

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.link1

@Composable
fun ClickableLinkText(
    text: String,
    style: TextStyle = link1,
    onClick: () -> Unit
){
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)){
            append(text)
        }
    }

    ClickableText(
        text = annotatedString,
        style = style,
        onClick = { offset ->
            onClick()
        }
    )
}