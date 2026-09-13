package com.rah885.kaamsetu.ui.screens.customer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.rah885.kaamsetu.data.database.KaamSetuDatabase
import com.rah885.kaamsetu.data.database.NotificationRepository
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.concurrent.Executors

data class ServiceRequestData(
    val id: Long = System.currentTimeMillis(),
    val service: String,
    val workerName: String,
    val customerName: String,
    val mobile: String,
    val description: String,
    val location: String,
    val dateTime: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val status: String = "रिक्वेस्ट भेजी गई",
    val price: String = "",
    val rating: Int = 0,
    val review: String = ""
)

@Composable
fun ServiceRequestScreen(
    selectedService: String = "",
    selectedWorker: String = "",
    onRequestSubmitted: (ServiceRequestData) -> Unit = {}
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var service by rememberSaveable {
        mutableStateOf(selectedService)
    }

    var customerName by rememberSaveable {
        mutableStateOf("")
    }

    var mobile by rememberSaveable {
        mutableStateOf("")
    }

    var description by rememberSaveable {
        mutableStateOf("")
    }

    var location by rememberSaveable {
        mutableStateOf("")
    }

    var dateTime by rememberSaveable {
        mutableStateOf("")
    }

    var latitude by rememberSaveable {
        mutableStateOf(0.0)
    }

    var longitude by rememberSaveable {
        mutableStateOf(0.0)
    }

    var locationLoading by rememberSaveable {
        mutableStateOf(false)
    }

    var locationMessage by rememberSaveable {
        mutableStateOf("")
    }

    var requestSubmitted by rememberSaveable {
        mutableStateOf(false)
    }

    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val fineGranted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true

            val coarseGranted =
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            if (fineGranted || coarseGranted) {
                getCurrentLocation(
                    context = context,
                    onLoading = {
                        locationLoading = it
                    },
                    onLocation = { lat, lon, address ->

                        latitude = lat
                        longitude = lon

                        if (address.isNotBlank()) {
                            location = address
                        }

                        locationMessage =
                            "✅ Location मिल गई।"
                    },
                    onError = {
                        locationMessage = it
                    }
                )
            } else {
                locationMessage =
                    "⚠️ Location permission जरूरी है।"
            }
        }

    LaunchedEffect(Unit) {

        val fineGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        val coarseGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (fineGranted || coarseGranted) {
            // Permission पहले से दी हुई है।
            // Location तभी ली जाएगी जब user button दबाएगा।
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "सर्विस रिक्वेस्ट",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "अपने काम की जानकारी देकर स्थानीय कामगार से संपर्क करें।",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        if (selectedWorker.isNotBlank()) {

            Text(
                text = "👤 कामगार: $selectedWorker",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }

        OutlinedTextField(
            value = service,
            onValueChange = {
                service = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("कौन-सी सेवा चाहिए?")
            },
            placeholder = {
                Text("जैसे इलेक्ट्रिशियन, प्लंबर")
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = customerName,
            onValueChange = {
                customerName = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("आपका नाम")
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = mobile,
            onValueChange = {
                if (it.length <= 10) {
                    mobile = it.filter { char ->
                        char.isDigit()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("मोबाइल नंबर")
            },
            placeholder = {
                Text("10 अंकों का मोबाइल नंबर")
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("काम की जानकारी")
            },
            placeholder = {
                Text("समस्या या काम के बारे में बताएं")
            },
            minLines = 4
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {

                val fineGranted =
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED

                val coarseGranted =
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED

                if (fineGranted || coarseGranted) {

                    getCurrentLocation(
                        context = context,
                        onLoading = {
                            locationLoading = it
                        },
                        onLocation = { lat, lon, address ->

                            latitude = lat
                            longitude = lon

                            if (address.isNotBlank()) {
                                location = address
                            }

                            locationMessage =
                                "✅ Location मिल गई।"
                        },
                        onError = {
                            locationMessage = it
                        }
                    )

                } else {

                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !locationLoading
        ) {
            Text(
                if (locationLoading) {
                    "📍 Location मिल रही है..."
                } else {
                    "📍 मेरी वर्तमान लोकेशन लें"
                }
            )
        }

        if (locationMessage.isNotBlank()) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = locationMessage,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (latitude != 0.0 && longitude != 0.0) {

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "📌 Coordinates: $latitude, $longitude",
                style = MaterialTheme.typography.labelMedium
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = location,
            onValueChange = {
                location = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("काम की जगह / पता")
            },
            placeholder = {
                Text("GPS से पता आएगा या अपना पता लिखें")
            },
            minLines = 2
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = dateTime,
            onValueChange = {
                dateTime = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("पसंदीदा तारीख / समय")
            },
            placeholder = {
                Text("जैसे 10 सितंबर, शाम 5 बजे")
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "💰 भुगतान कामगार द्वारा कीमत तय होने के बाद होगा।",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {

                val request = ServiceRequestData(
                    service = service,
                    workerName = selectedWorker,
                    customerName = customerName,
                    mobile = mobile,
                    description = description,
                    location = location,
                    dateTime = dateTime,
                    latitude = latitude,
                    longitude = longitude
                )

                onRequestSubmitted(request)

                /*
                 * Customer ने Worker को नई Service Request भेजी।
                 * अभी Worker Account ID उपलब्ध नहीं है,
                 * इसलिए selectedWorker का नाम recipient key के रूप में
                 * इस्तेमाल किया जा रहा है।
                 */
                if (selectedWorker.isNotBlank()) {

                    scope.launch {

                        val database =
                            KaamSetuDatabase.getInstance(context)

                        val repository =
                            NotificationRepository(database)

                        repository.createNotification(
                            recipientId = selectedWorker,
                            senderId = mobile,
                            recipientRole = "WORKER",
                            type = "NEW_SERVICE_REQUEST",
                            title = "🔔 नई सर्विस रिक्वेस्ट",
                            message = "$customerName ने $service के लिए आपको नई सर्विस रिक्वेस्ट भेजी है।",
                            referenceId = request.id.toString()
                        )
                    }
                }

                requestSubmitted = true
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = service.isNotBlank() &&
                    customerName.isNotBlank() &&
                    mobile.length == 10 &&
                    description.isNotBlank() &&
                    location.isNotBlank()
        ) {
            Text("रिक्वेस्ट भेजें")
        }

        if (requestSubmitted) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "✅ आपकी सर्विस रिक्वेस्ट भेज दी गई है।",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "अब कामगार आपकी रिक्वेस्ट देख सकता है।",
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun getCurrentLocation(
    context: Context,
    onLoading: (Boolean) -> Unit,
    onLocation: (Double, Double, String) -> Unit,
    onError: (String) -> Unit
) {

    val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val fineGranted =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    val coarseGranted =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    if (!fineGranted && !coarseGranted) {
        onError("⚠️ Location permission नहीं मिली।")
        return
    }

    val provider = when {

        locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER
        ) -> LocationManager.GPS_PROVIDER

        locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        ) -> LocationManager.NETWORK_PROVIDER

        else -> null
    }

    if (provider == null) {
        onError("⚠️ Phone की Location/GPS बंद है। कृपया Location चालू करें।")
        return
    }

    onLoading(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

        val cancellationSignal =
            android.os.CancellationSignal()

        locationManager.getCurrentLocation(
            provider,
            cancellationSignal,
            Executors.newSingleThreadExecutor()
        ) { currentLocation ->

            onLoading(false)

            if (currentLocation == null) {

                onError(
                    "⚠️ Current location नहीं मिल पाई। कृपया बाहर/खुले स्थान पर दोबारा कोशिश करें।"
                )

            } else {

                val lat = currentLocation.latitude
                val lon = currentLocation.longitude

                val address =
                    getAddressFromCoordinates(
                        context,
                        lat,
                        lon
                    )

                onLocation(
                    lat,
                    lon,
                    address
                )
            }
        }

    } else {

        val lastLocation = try {

            locationManager.getLastKnownLocation(
                provider
            )

        } catch (exception: SecurityException) {
            null
        }

        onLoading(false)

        if (lastLocation == null) {

            onError(
                "⚠️ Location नहीं मिल पाई। कृपया GPS चालू करके दोबारा कोशिश करें।"
            )

        } else {

            val lat = lastLocation.latitude
            val lon = lastLocation.longitude

            val address =
                getAddressFromCoordinates(
                    context,
                    lat,
                    lon
                )

            onLocation(
                lat,
                lon,
                address
            )
        }
    }
}

private fun getAddressFromCoordinates(
    context: Context,
    latitude: Double,
    longitude: Double
): String {

    return try {

        val geocoder =
            Geocoder(
                context,
                Locale.getDefault()
            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            var result = ""

            geocoder.getFromLocation(
                latitude,
                longitude,
                1
            ) { addresses ->

                if (addresses.isNotEmpty()) {
                    result =
                        addresses[0].getAddressLine(0) ?: ""
                }
            }

            result

        } else {

            @Suppress("DEPRECATION")
            val addresses =
                geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1
                )

            @Suppress("DEPRECATION")
            if (!addresses.isNullOrEmpty()) {
                addresses[0].getAddressLine(0) ?: ""
            } else {
                ""
            }
        }

    } catch (exception: Exception) {
        ""
    }
}
