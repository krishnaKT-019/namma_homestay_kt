package com.example.nammahomestay.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Dashboard : Screen("dashboard")
    object Profile : Screen("profile")
    object Menu : Screen("menu")
    object Inquiries : Screen("inquiries")
    object LocalGuide : Screen("local_guide")
}
