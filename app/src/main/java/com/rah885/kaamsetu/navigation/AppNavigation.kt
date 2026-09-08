package com.rah885.kaamsetu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rah885.kaamsetu.ui.screens.home.HomeScreen

object AppRoutes {
    const val HOME = "home"
    const val SERVICES = "services"
    const val REQUESTS = "requests"
    const val NOTIFICATIONS = "notifications"
    const val PROFILE = "profile"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                onCustomerClick = {
                    navController.navigate(AppRoutes.SERVICES)
                },
                onWorkerClick = {
                    navController.navigate(AppRoutes.PROFILE)
                }
            )
        }

        composable(AppRoutes.SERVICES) {
            PlaceholderScreen("सेवाएँ")
        }

        composable(AppRoutes.REQUESTS) {
            PlaceholderScreen("मेरी रिक्वेस्ट")
        }

        composable(AppRoutes.NOTIFICATIONS) {
            PlaceholderScreen("नोटिफिकेशन")
        }

        composable(AppRoutes.PROFILE) {
            PlaceholderScreen("प्रोफाइल")
        }
    }
}

@Composable
private fun PlaceholderScreen(title: String) {
    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        androidx.compose.material3.Text(
            text = title,
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
    }
}
