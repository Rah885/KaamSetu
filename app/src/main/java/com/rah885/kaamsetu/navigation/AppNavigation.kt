package com.rah885.kaamsetu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rah885.kaamsetu.ui.screens.customer.CustomerMainScreen
import com.rah885.kaamsetu.ui.screens.customer.ServiceRequestData
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

    /*
     * Customer और Worker दोनों के लिए
     * एक ही shared request list.
     */
    val serviceRequests = remember {
        mutableStateListOf<ServiceRequestData>()
    }

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

            CustomerMainScreen(
                submittedRequests = serviceRequests,

                onRequestSubmitted = { request ->
                    serviceRequests.add(request)
                },

                onRequestUpdated = { updatedRequest ->

                    val index = serviceRequests.indexOfFirst {
                        it.id == updatedRequest.id
                    }

                    if (index >= 0) {
                        serviceRequests[index] = updatedRequest
                    }
                }
            )
        }

        composable(AppRoutes.WORKER) {

            WorkerMainScreen(
                serviceRequests = serviceRequests,
                onRequestUpdated = { updatedRequest ->

                    val index = serviceRequests.indexOfFirst {
                        it.id == updatedRequest.id
                    }

                    if (index >= 0) {
                        serviceRequests[index] = updatedRequest
                    }
                }
            )
        }
    }
}
