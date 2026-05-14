package com.example.nammahomestay.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nammahomestay.presentation.screens.DailyMenuScreen
import com.example.nammahomestay.presentation.screens.DashboardScreen
import com.example.nammahomestay.presentation.screens.InquiryBoxScreen
import com.example.nammahomestay.presentation.screens.LocalGuideScreen
import com.example.nammahomestay.presentation.screens.MyHomeProfileScreen
import com.example.nammahomestay.presentation.screens.SplashScreen

@Composable
fun HomestayNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onNavigateToDashboard = {
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToMenu = { navController.navigate(Screen.Menu.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                onNavigateToInquiries = { navController.navigate(Screen.Inquiries.route) },
                onNavigateToLocalGuide = { navController.navigate(Screen.LocalGuide.route) }
            )
        }
        composable(Screen.Menu.route) {
            DailyMenuScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.Profile.route) {
            MyHomeProfileScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.Inquiries.route) {
            InquiryBoxScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.LocalGuide.route) {
            LocalGuideScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
