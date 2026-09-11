package com.rah885.kaamsetu.ui.screens.worker

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

    /*
     * Worker Profile के अंदर की screens
     */
    var profileSubScreen by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        bottomBar = {

            /*
             * Profile के अंदर कोई screen खुली हो
             * तो नीचे NavigationBar छिपेगी।
             */
            if (profileSubScreen == null) {

                NavigationBar {

                    navItems.forEachIndexed { index, item ->

                        NavigationBarItem(
                            selected = selectedIndex == index,

                            onClick = {
                                selectedIndex = index
                                profileSubScreen = null
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
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            /*
             * Profile के अंदर की screens
             */
            when (profileSubScreen) {

                "edit_profile" -> {

                    BackHandler {
                        profileSubScreen = null
                    }

                    WorkerEditProfileScreen()
                }

                "settings" -> {

                    BackHandler {
                        profileSubScreen = null
                    }

                    WorkerSettingsScreen()
                }

                "help" -> {

                    BackHandler {
                        profileSubScreen = null
                    }

                    WorkerHelpSupportScreen()
                }

                else -> {

                    /*
                     * Existing Worker screens
                     */
                    when (selectedIndex) {

                        0 -> {
                            WorkerDashboardScreen()
                        }

                        1 -> {
                            NewRequestsScreen(
                                serviceRequests = serviceRequests,
                                onRequestUpdated = onRequestUpdated
                            )
                        }

                        2 -> {
                            MyJobsScreen()
                        }

                        3 -> {
                            EarningsScreen()
                        }

                        4 -> {

                            WorkerProfileScreen(

                                onEditProfileClick = {
                                    profileSubScreen = "edit_profile"
                                },

                                onSettingsClick = {
                                    profileSubScreen = "settings"
                                },

                                onHelpClick = {
                                    profileSubScreen = "help"
                                },

                                onLogoutClick = {

                                    /*
                                     * अभी Login/Auth system नहीं है।
                                     * इसलिए Logout फिलहाल Worker Home
                                     * पर वापस ले जाएगा।
                                     *
                                     * बाद में proper Login/Sign Up/Auth
                                     * जुड़ने पर यही जगह real logout होगी।
                                     */
                                    profileSubScreen = null
                                    selectedIndex = 0
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
