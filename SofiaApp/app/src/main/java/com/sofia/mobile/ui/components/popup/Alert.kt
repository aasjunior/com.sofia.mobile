package com.sofia.mobile.ui.components.popup

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Alert(
    title: String,
    msg: String,
    showDialog: Boolean,
    onDismiss: () -> Unit
){
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
            text = { Text(msg) },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("Ok")
                }
            }
        )
    }
}