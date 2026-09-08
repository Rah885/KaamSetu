package com.rah885.kaamsetu.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
