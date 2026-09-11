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

    var profileSubScreen by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    // Worker profile data
    var profileName by rememberSaveable {
        mutableStateOf("")
    }

    var profileMobile by rememberSaveable {
        mutableStateOf("")
    }

    var profileService by rememberSaveable {
        mutableStateOf("")
    }

    var profileAddress by rememberSaveable {
        mutableStateOf("")
    }

    var profileCity by rememberSaveable {
        mutableStateOf("")
    }

    var profilePhotoUri by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        bottomBar = {
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
                                Text(item.iconText)
                            },
                            label = {
                                Text(item.title)
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

            when (profileSubScreen) {

                "edit_profile" -> {
                    BackHandler {
                        profileSubScreen = null
                    }

                    WorkerEditProfileScreen(
                        initialName = profileName,
                        initialMobile = profileMobile,
                        initialService = profileService,
                        initialAddress = profileAddress,
                        initialCity = profileCity,
                        initialPhotoUri = profilePhotoUri,
                        onSave = { name, mobile, service, address, city, photoUri ->

                            profileName = name
                            profileMobile = mobile
                            profileService = service
                            profileAddress = address
                            profileCity = city
                            profilePhotoUri = photoUri

                            profileSubScreen = null
                        }
                    )
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
                                name = profileName,
                                mobile = profileMobile,
                                service = profileService,
                                address = profileAddress,
                                city = profileCity,
                                photoUri = profilePhotoUri,

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
