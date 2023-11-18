package com.sofia.mobile.ui.components.buttons

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.ui.theme.Gray3
import com.sofia.mobile.ui.theme.SoftPurple

@Composable
fun FloatingAddButton(onClick: () -> Unit){
    SmallFloatingActionButton(
        onClick = onClick,
        containerColor = SoftPurple,
        contentColor = Gray3,
        elevation = FloatingActionButtonDefaults.elevation(4.dp),
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Small floating action add button"
        )
    }
}

@Preview
@Composable
private fun FloatingAddButtonPreview(){
    FloatingAddButton(onClick = {})
}