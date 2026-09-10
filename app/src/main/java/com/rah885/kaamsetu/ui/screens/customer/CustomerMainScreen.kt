package com.rah885.kaamsetu.ui.screens.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private data class CustomerNavItem(
    val title: String,
    val iconText: String
)

@Composable
fun CustomerMainScreen(
    submittedRequests: List<ServiceRequestData>,
    onRequestSubmitted: (ServiceRequestData) -> Unit
) {

    val navItems = listOf(
        CustomerNavItem("Home", "⌂"),
        CustomerNavItem("Services", "🔧"),
        CustomerNavItem("Requests", "📋"),
        CustomerNavItem("Alerts", "🔔"),
        CustomerNavItem("Profile", "👤")
    )

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    var selectedService by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var selectedWorker by remember {
        mutableStateOf<WorkerProfileData?>(null)
    }

    var showServiceRequest by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {

                navItems.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = selectedIndex == index,

                        onClick = {
                            selectedIndex = index
                            selectedService = null
                            selectedWorker = null
                            showServiceRequest = false
                        },

                        icon = {
                            Text(
                                text = item.iconText
                            )
                        },

                        label = {
                            Text(
                                text = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            when (selectedIndex) {

                // =========================
                // HOME
                // =========================
                0 -> {
                    CustomerHomeScreen()
                }

                // =========================
                // SERVICES
                // Services → Workers → Profile → Request
                // =========================
                1 -> {

                    when {

                        showServiceRequest -> {

                            BackHandler {
                                showServiceRequest = false
                            }

                            ServiceRequestScreen(
                                selectedService = selectedService ?: "",
                                selectedWorker = selectedWorker?.name ?: "",

                                onRequestSubmitted = { request ->

                                    onRequestSubmitted(request)

                                    showServiceRequest = false
                                    selectedWorker = null
                                }
                            )
                        }

                        selectedWorker != null -> {

                            val worker = selectedWorker!!

                            BackHandler {
                                selectedWorker = null
                            }

                            WorkerDetailsScreen(
                                workerName = worker.name,
                                serviceName = worker.service,
                                rating = worker.rating,
                                distance = worker.distance,
                                available = worker.available,

                                onRequestClick = {
                                    showServiceRequest = true
                                }
                            )
                        }

                        selectedService != null -> {

                            BackHandler {
                                selectedService = null
                            }

                            ServiceWorkersScreen(
                                serviceName = selectedService!!,

                                onWorkerClick = {
                                        workerName,
                                        workerService,
                                        workerRating,
                                        workerDistance,
                                        workerAvailable ->

                                    selectedWorker = WorkerProfileData(
                                        name = workerName,
                                        service = workerService,
                                        rating = workerRating,
                                        distance = workerDistance,
                                        available = workerAvailable
                                    )
                                }
                            )
                        }

                        else -> {

                            ServicesScreen(
                                onServiceClick = { serviceName ->

                                    selectedService = serviceName
                                }
                            )
                        }
                    }
                }

                // =========================
                // REQUESTS
                // =========================
                2 -> {

                    MyRequestsScreen(
                        submittedRequests = submittedRequests
                    )
                }

                // =========================
                // ALERTS
                // =========================
                3 -> {
                    NotificationsScreen()
                }

                // =========================
                // PROFILE
                // =========================
                4 -> {
                    ProfileScreen()
                }
            }
        }
    }
}
