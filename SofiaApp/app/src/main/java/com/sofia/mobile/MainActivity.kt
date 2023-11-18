package com.sofia.mobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sofia.mobile.ui.screens.HomeScreen
import com.sofia.mobile.ui.screens.PatientListScreen
import com.sofia.mobile.ui.screens.PatientProfileScreen
import com.sofia.mobile.ui.screens.PatientRegistrationScreen
import com.sofia.mobile.ui.screens.SplashScreen
import com.sofia.mobile.ui.theme.SofiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SofiaTheme(darkTheme = false){
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "splash") {
                        composable("splash") { SplashScreen(navController) }
                        composable("home") { HomeScreen(navController) }
                        composable("patientList") { PatientListScreen(navController) }
                        composable("patientRegistration") { PatientRegistrationScreen(navController) }
                        composable("patientProfile/{patientId}") { backStackEntry ->
                            val patientId = backStackEntry.arguments?.getString("patientId")?.toLongOrNull()
                            val context = LocalContext.current
                            if(patientId != null) {
                                PatientProfileScreen(navController, patientId)
                            } else {
                                // Exiba um Toast com uma mensagem de erro
                                LaunchedEffect(Unit) {
                                    Toast.makeText(context, "Erro: ID do paciente inválido.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}