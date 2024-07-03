package com.fakhrirasyids.quickie.platform.ui.navigation

sealed class Screen(val route: String) {
    data object Splash: Screen("splash")
    data object Home: Screen("home")
}