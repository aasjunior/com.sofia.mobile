package com.sofia.mobile.ui.view.components.popup

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.Gray3

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
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrillantPurple,
                        contentColor = Gray3
                    )
                ) {
                    Text(stringResource(id = R.string.form_yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrillantPurple,
                        contentColor = Gray3
                    )
                ) {
                    Text(stringResource(id = R.string.form_no))
                }
            }
        )
    }
}