package com.sofia.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.ui.screens.HomeScreen
import com.sofia.mobile.ui.screens.PatientListScreen
import com.sofia.mobile.ui.screens.PatientRegistrationScreen
import com.sofia.mobile.ui.screens.SplashScreen
import com.sofia.mobile.ui.theme.SofiaTheme
import com.sofia.mobile.ui.viewmodels.PatientViewModel
import com.sofia.mobile.ui.viewmodels.SearchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val searchViewModel: SearchViewModel by viewModels()
        val patientViewModel by viewModels<PatientViewModel>()
        setContent {
            SofiaTheme(darkTheme = false){
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "splash") {
                        composable("splash") { SplashScreen(navController) }
                        composable("home") { HomeScreen(navController) }
                        composable("patientList") { PatientListScreen(navController, nPatient = 3) } // substitua 0 pelo número de pacientes
                        composable("patientRegistration") { PatientRegistrationScreen(navController) }
                    }
                }
            }
        }
    }
}