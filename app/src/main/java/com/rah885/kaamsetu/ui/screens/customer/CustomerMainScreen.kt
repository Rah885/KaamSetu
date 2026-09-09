package com.rah885.kaamsetu.ui.screens.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp

private data class CustomerNavItem(
    val title: String,
    val iconText: String
)

@Composable
fun CustomerMainScreen() {

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

    Scaffold(
        bottomBar = {
            NavigationBar {

                navItems.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = selectedIndex == index,

                        onClick = {
                            selectedIndex = index

                            // नई tab खोलते समय Services का अंदर वाला
                            // selection reset रहेगा
                            selectedService = null
                            selectedWorker = null
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

                // -------------------------
                // HOME
                // -------------------------
                0 -> {
                    CustomerHomeContent()
                }

                // -------------------------
                // SERVICES
                // Services → Workers → Profile
                // -------------------------
                1 -> {

                    when {

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
                                available = worker.available
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

                // -------------------------
                // REQUESTS
                // -------------------------
                2 -> {
                    MyRequestsScreen()
                }

                // -------------------------
                // ALERTS / NOTIFICATIONS
                // -------------------------
                3 -> {
                    NotificationsScreen()
                }

                // -------------------------
                // PROFILE
                // -------------------------
                4 -> {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
private fun CustomerHomeContent() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "कामसेतु",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "आपके काम का सही साथी",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
