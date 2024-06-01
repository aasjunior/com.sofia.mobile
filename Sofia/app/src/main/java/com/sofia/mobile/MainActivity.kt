package com.sofia.mobile

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.ui.view.MainCompose
import com.sofia.mobile.ui.view.contents.RelativeDimensions
import com.sofia.mobile.ui.view.contents.containers.BaseContent
import com.sofia.mobile.ui.view.screens.intro.GettingStartedScreen
import com.sofia.mobile.ui.view.screens.main.CheckListResultScreen
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel
import com.sofia.mobile.ui.viewmodel.LoginViewModel

class MainActivity : ComponentActivity(){
    private val imgvm: ImagePickerViewModel by viewModels()
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imgvm.selectImage(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            val rd = RelativeDimensions(screenWidth)
            val loginViewModel: LoginViewModel = viewModel()

            MainCompose(
                rd = rd,
                loginViewModel = loginViewModel,
                imagePickerViewModel = imgvm,
                onPickImageClick = { launcher.launch("image/*") }
            )
        }
    }
}