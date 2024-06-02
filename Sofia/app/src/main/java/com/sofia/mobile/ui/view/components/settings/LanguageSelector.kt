package com.sofia.mobile.ui.view.components.settings

import android.app.ActivityManager
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import com.sofia.mobile.R
import com.sofia.mobile.config.Injector
import com.sofia.mobile.config.preferences.language.Language
import com.sofia.mobile.ui.navigation.routes.MainNavOptions
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.view.components.buttons.CustomButton
import com.sofia.mobile.ui.view.components.popup.CustomAlertDialog
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.h3
import com.sofia.mobile.ui.view.components.textstyles.SofiaTextStyles.text1

@Composable
fun LanguageSelector(navController: NavController){
    val languageManager = remember { Injector.provideLanguageManager() }
    val currentLanguage = languageManager.currentLanguage.collectAsState()
    val selectedLanguage = rememberSaveable { mutableStateOf(currentLanguage.value) }
    val showDialog = remember { mutableStateOf(false) }

    Text(
        text= stringResource(id = R.string.change_language),
        style = h3
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
        Language.values().forEach { language ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .selectable(
                        selected = (language == currentLanguage.value),
                        onClick = {
                            if(language == currentLanguage.value)
                                return@selectable
                            selectedLanguage.value = language
                            showDialog.value = true
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = (language == currentLanguage.value),
                    colors = RadioButtonDefaults.colors(BrillantPurple),
                    onClick = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = language.resId),
                    style = text1.copy(BrillantPurple)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 25.dp),
            horizontalArrangement = Arrangement.End
        ){
            CustomButton(text = stringResource(id = R.string.btn_back)) {
                navController.navigate(MainNavOptions.HomeScreen.name)
            }
        }

        if(showDialog.value){
            CustomAlertDialog(
                onDismissRequest = { !showDialog.value },
                text = stringResource(
                    id = R.string.alert_confirm_description,
                    stringResource(id = R.string.alert_change_settings)
                )
            ) {
                languageManager.updateLanguage(selectedLanguage.value)
                showDialog.value = false
            }
        }
    }
}

private fun restartActivity(context: Context, isLoggedIn: Boolean){
    val activityManager = ContextCompat
        .getSystemService(context, ActivityManager::class.java)

    val appTasks = activityManager?.appTasks

    val launchIntent = context.packageManager
        .getLaunchIntentForPackage(context.packageName)

    launchIntent?.putExtra("isLoggedIn", isLoggedIn)
    appTasks?.get(0)?.finishAndRemoveTask()
    appTasks?.get(0)?.startActivity(context, launchIntent, null)
}