package com.sofia.mobile.ui.view.components.form.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple

@Composable
fun RoundedCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedColor: Color = BrillantPurple,
    checkedColor: Color = BrillantPurple
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(if (checked) checkedColor else Color.Transparent)
            .border(2.dp, uncheckedColor, CircleShape)
            .clickable(enabled) { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Checked",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun RoundCheckboxPreview(){
    Row{
        RoundedCheckbox(checked = false, onCheckedChange = {})
        RoundedCheckbox(checked = true, onCheckedChange = {})
    }
}