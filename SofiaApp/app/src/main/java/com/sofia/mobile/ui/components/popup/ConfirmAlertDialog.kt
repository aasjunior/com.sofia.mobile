package com.sofia.mobile.ui.components.popup

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ConfirmAlertDialog(
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(true) }

    if(openDialog.value){
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                onDismiss()
            },
            title = { Text(title) },
            text = { Text(text) },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        openDialog.value = false
                    }
                ) {
                    Text("Sim")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        onDismiss()
                    }
                ) {
                    Text("Não")
                }
            }
        )
    }
}
