package com.sofia.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.ui.screens.HomeScreen
import com.sofia.mobile.ui.screens.SplashScreen
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.viewmodels.SearchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchViewModel: SearchViewModel by viewModels()
        setContent {
            SofiaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    //SplashScreen(rememberNavController())
                    HomeScreen(rememberNavController()/*, searchViewModel*/)
                }
            }
        }
    }
}