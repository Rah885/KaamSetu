package com.rah885.kaamsetu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rah885.kaamsetu.ui.screens.customer.CustomerMainScreen
import com.rah885.kaamsetu.ui.screens.home.HomeScreen
import com.rah885.kaamsetu.ui.screens.worker.WorkerMainScreen

object AppRoutes {
    const val HOME = "home"
    const val CUSTOMER = "customer"
    const val WORKER = "worker"
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
                    navController.navigate(AppRoutes.CUSTOMER)
                },
                onWorkerClick = {
                    navController.navigate(AppRoutes.WORKER)
                }
            )
        }

        composable(AppRoutes.CUSTOMER) {
            CustomerMainScreen()
        }

        composable(AppRoutes.WORKER) {
            WorkerMainScreen()
        }
    }
}
