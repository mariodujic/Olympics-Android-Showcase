package com.zero.olympics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zero.olympics.navigation.AthleteDetails
import com.zero.olympics.navigation.Athletes
import com.zero.olympics.navigation.Splash
import com.zero.olympics.screens.athletes.details.presentation.ui.AthleteDetailsScreen
import com.zero.olympics.screens.athletes.list.presentation.ui.AthletesScreen
import com.zero.olympics.screens.common.ui.OlympicsTheme
import com.zero.olympics.screens.splash.presentation.ui.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OlympicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Splash.route) {
                        composable(Splash.route) {
                            SplashScreen {
                                navController.popBackStack()
                                navController.navigate(Athletes.route)
                            }
                        }
                        composable(Athletes.route) {
                            AthletesScreen {
                                navController.navigate(AthleteDetails.ROUTE_WITHOUT_ARGUMENT + it)
                            }
                        }
                        composable(AthleteDetails.route) {
                            AthleteDetailsScreen{
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}