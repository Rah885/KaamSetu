package com.rah885.kaamsetu.ui.screens.worker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import com.rah885.kaamsetu.ui.screens.customer.ServiceRequestData

private data class WorkerNavItem(
    val title: String,
    val iconText: String
)

@Composable
fun WorkerMainScreen(
    serviceRequests: List<ServiceRequestData>,
    onRequestUpdated: (ServiceRequestData) -> Unit
) {

    val navItems = listOf(
        WorkerNavItem("Home", "🏠"),
        WorkerNavItem("Requests", "📋"),
        WorkerNavItem("Jobs", "🔧"),
        WorkerNavItem("Earnings", "💰"),
        WorkerNavItem("Profile", "👤")
    )

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {

                navItems.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
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
                .padding(innerPadding)
        ) {

            when (selectedIndex) {

                0 -> WorkerDashboardScreen()

                1 -> NewRequestsScreen(
                    serviceRequests = serviceRequests,
                    onRequestUpdated = onRequestUpdated
                )

                2 -> MyJobsScreen()

                3 -> EarningsScreen()

                4 -> WorkerProfileScreen()
            }
        }
    }
}
