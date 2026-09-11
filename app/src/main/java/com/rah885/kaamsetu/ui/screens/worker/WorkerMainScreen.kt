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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rah885.kaamsetu.data.database.AppDataEntity
import com.rah885.kaamsetu.data.database.KaamSetuDatabase
import com.rah885.kaamsetu.ui.screens.customer.ServiceRequestData
import kotlinx.coroutines.launch

private const val WORKER_PROFILE_NAME = "worker_profile_name"
private const val WORKER_PROFILE_MOBILE = "worker_profile_mobile"
private const val WORKER_PROFILE_SERVICE = "worker_profile_service"
private const val WORKER_PROFILE_ADDRESS = "worker_profile_address"
private const val WORKER_PROFILE_CITY = "worker_profile_city"
private const val WORKER_PROFILE_PHOTO = "worker_profile_photo"

private data class WorkerNavItem(
    val title: String,
    val iconText: String
)

@Composable
fun WorkerMainScreen(
    serviceRequests: List<ServiceRequestData>,
    onRequestUpdated: (ServiceRequestData) -> Unit
) {
    val context = LocalContext.current

    val database = remember {
        KaamSetuDatabase.getInstance(context)
    }

    val dao = remember {
        database.appDataDao()
    }

    val scope = rememberCoroutineScope()

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

    /*
     * Room से Worker Profile load करें।
     */
    LaunchedEffect(Unit) {

        val savedName = dao.get(WORKER_PROFILE_NAME)
        val savedMobile = dao.get(WORKER_PROFILE_MOBILE)
        val savedService = dao.get(WORKER_PROFILE_SERVICE)
        val savedAddress = dao.get(WORKER_PROFILE_ADDRESS)
        val savedCity = dao.get(WORKER_PROFILE_CITY)
        val savedPhoto = dao.get(WORKER_PROFILE_PHOTO)

        profileName = savedName?.value ?: ""
        profileMobile = savedMobile?.value ?: ""
        profileService = savedService?.value ?: ""
        profileAddress = savedAddress?.value ?: ""
        profileCity = savedCity?.value ?: ""
        profilePhotoUri = savedPhoto?.value
            ?.takeIf { it.isNotBlank() }
    }

    /*
     * Worker Profile का latest data Room में save करें।
     */
    fun saveWorkerProfile(
        name: String,
        mobile: String,
        service: String,
        address: String,
        city: String,
        photoUri: String?
    ) {
        scope.launch {

            dao.save(
                AppDataEntity(
                    key = WORKER_PROFILE_NAME,
                    value = name
                )
            )

            dao.save(
                AppDataEntity(
                    key = WORKER_PROFILE_MOBILE,
                    value = mobile
                )
            )

            dao.save(
                AppDataEntity(
                    key = WORKER_PROFILE_SERVICE,
                    value = service
                )
            )

            dao.save(
                AppDataEntity(
                    key = WORKER_PROFILE_ADDRESS,
                    value = address
                )
            )

            dao.save(
                AppDataEntity(
                    key = WORKER_PROFILE_CITY,
                    value = city
                )
            )

            dao.save(
                AppDataEntity(
                    key = WORKER_PROFILE_PHOTO,
                    value = photoUri ?: ""
                )
            )
        }
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

                        onSave = {
                                name,
                                mobile,
                                service,
                                address,
                                city,
                                photoUri ->

                            /*
                             * Screen पर तुरंत latest data दिखाएँ।
                             */
                            profileName = name
                            profileMobile = mobile
                            profileService = service
                            profileAddress = address
                            profileCity = city
                            profilePhotoUri = photoUri

                            /*
                             * Room में permanent save करें।
                             */
                            saveWorkerProfile(
                                name = name,
                                mobile = mobile,
                                service = service,
                                address = address,
                                city = city,
                                photoUri = photoUri
                            )

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
