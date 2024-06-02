package com.sofia.mobile.ui.view.components.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sofia.mobile.R
import com.sofia.mobile.ui.view.components.buttons.CustomButton
import com.sofia.mobile.ui.view.components.buttons.CustomOutlinedButton
import com.sofia.mobile.ui.view.components.cards.FloatCard
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1

@Composable
fun ConfirmAlertDialog(
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(true) }

    val yes = stringResource(id = R.string.form_yes)
    val no = stringResource(id = R.string.form_no)

    if(openDialog.value){
        Dialog(onDismissRequest = {
            openDialog.value = false
            onDismiss()
        }) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = title,
                        style = h3.copy(Color.Black)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = text,
                        style = text1.copy(Color.Black)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomOutlinedButton(
                            modifier = Modifier.width(100.dp),
                            text = no
                        ) {
                            openDialog.value = false
                            onDismiss()
                        }
                        CustomButton(
                            modifier = Modifier.width(100.dp),
                            text = yes
                        ) {
                            onConfirm()
                            openDialog.value = false
                        }
                    }
                }
            }
        }
    }
}
