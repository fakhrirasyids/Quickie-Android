package com.fakhrirasyids.quickie.platform.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fakhrirasyids.quickie.platform.ui.navigation.Screen
import com.fakhrirasyids.quickie.platform.ui.screens.home.HomeScreen
import com.fakhrirasyids.quickie.platform.ui.screens.splash.SplashScreen

@Composable
fun QuickieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            NavHost(navController = navController, startDestination = Screen.Splash.route) {

                // Splash Route
                composable(route = Screen.Splash.route) {
                    SplashScreen(
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Splash.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                // Home Route
                composable(route = Screen.Home.route) {
                    HomeScreen()
                }
            }
        }
    }
}