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
    onRequestSubmitted: (ServiceRequestData) -> Unit,
    onRequestUpdated: (ServiceRequestData) -> Unit,
    payments: List<PaymentData>,
    onPaymentSuccess: (PaymentData) -> Unit
) {

    val navItems = listOf(
        CustomerNavItem("Home", "⌂"),
        CustomerNavItem("Services", "🔧"),
        CustomerNavItem("Requests", "📋"),
        CustomerNavItem("Alerts", "🔔"),
        CustomerNavItem("Payments", "💳"),
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

    var selectedPaymentRequest by remember {
        mutableStateOf<ServiceRequestData?>(null)
    }

    /*
     * =========================
     * PROFILE DATA
     * =========================
     */

    var profileName by rememberSaveable {
        mutableStateOf("")
    }

    var profileMobile by rememberSaveable {
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
     * Profile के अंदर की screens
     */

    var profileSubScreen by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        bottomBar = {

            /*
             * जब Profile के अंदर कोई screen खुली हो,
             * तब नीचे की navigation bar छिपा देंगे।
             */

            if (profileSubScreen == null) {

                NavigationBar {

                    navItems.forEachIndexed { index, item ->

                        NavigationBarItem(
                            selected = selectedIndex == index,

                            onClick = {

                                selectedIndex = index

                                selectedService = null
                                selectedWorker = null
                                showServiceRequest = false
                                selectedPaymentRequest = null
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
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            /*
             * =========================
             * PROFILE SUB SCREENS
             * =========================
             */

            when (profileSubScreen) {

                "edit_profile" -> {

                    BackHandler {
                        profileSubScreen = null
                    }

                    EditProfileScreen(
                        initialName = profileName,
                        initialMobile = profileMobile,
                        initialAddress = profileAddress,
                        initialCity = profileCity,
                        initialPhotoUri = profilePhotoUri,

                        onSave = {
                                name,
                                mobile,
                                address,
                                city,
                                photoUri ->

                            profileName = name
                            profileMobile = mobile
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

                    SettingsScreen()
                }

                "help" -> {

                    BackHandler {
                        profileSubScreen = null
                    }

                    HelpSupportScreen()
                }

                else -> {

                    /*
                     * =========================
                     * PAYMENT SCREEN
                     * =========================
                     */

                    if (selectedPaymentRequest != null) {

                        PaymentScreen(
                            request = selectedPaymentRequest!!,

                            onPaymentSuccess = { payment ->

                                onPaymentSuccess(payment)

                                selectedPaymentRequest = null
                            },

                            onBack = {
                                selectedPaymentRequest = null
                            }
                        )

                    } else {

                        /*
                         * =========================
                         * MAIN CUSTOMER SCREENS
                         * =========================
                         */

                        when (selectedIndex) {

                            /*
                             * =========================
                             * HOME
                             * =========================
                             */

                            0 -> {

                                CustomerHomeScreen()
                            }

                            /*
                             * =========================
                             * SERVICES
                             * =========================
                             */

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

                                                selectedWorker =
                                                    WorkerProfileData(
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

                            /*
                             * =========================
                             * REQUESTS
                             * =========================
                             */

                            2 -> {

                                MyRequestsScreen(
                                    submittedRequests = submittedRequests,

                                    onRequestUpdated = onRequestUpdated,

                                    onPaymentClick = { request ->

                                        selectedPaymentRequest = request
                                    }
                                )
                            }

                            /*
                             * =========================
                             * ALERTS
                             * =========================
                             */

                            3 -> {

                                NotificationsScreen()
                            }

                            /*
                             * =========================
                             * PAYMENTS
                             * =========================
                             */

                            4 -> {

                                PaymentHistoryScreen(
                                    payments = payments,

                                    submittedRequests = submittedRequests,

                                    onPaymentClick = { request ->

                                        selectedPaymentRequest = request
                                    }
                                )
                            }

                            /*
                             * =========================
                             * PROFILE
                             * =========================
                             */

                            5 -> {

                                ProfileScreen(
                                    name = profileName,
                                    mobile = profileMobile,
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

                                        /*
                                         * अभी Login/Auth system नहीं है।
                                         * इसलिए Logout फिलहाल Customer Home पर
                                         * वापस ले जाएगा।
                                         *
                                         * असली Login/Sign Up बनने के बाद
                                         * यही जगह proper logout से जुड़ेगी।
                                         */

                                        profileSubScreen = null
                                        selectedIndex = 0
                                        selectedService = null
                                        selectedWorker = null
                                        showServiceRequest = false
                                        selectedPaymentRequest = null
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
