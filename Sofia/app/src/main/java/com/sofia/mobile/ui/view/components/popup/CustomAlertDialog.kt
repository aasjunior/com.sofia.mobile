package com.sofia.mobile.ui.view.components.popup

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme

@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    title: String = stringResource(id = R.string.alert_title),
    text: String,
    btnOnClick: () -> Unit

){
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = {
            Button(
                onClick = btnOnClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = SofiaColorScheme.BrillantPurple,
                    contentColor = SofiaColorScheme.Gray3
                )
            ) {
                Text("Ok")
            }
        }
    )
}