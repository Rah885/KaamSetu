package com.rah885.kaamsetu.ui.screens.customer

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ServiceRequestData(
    val service: String,
    val workerName: String,
    val customerName: String,
    val mobile: String,
    val description: String,
    val location: String,
    val dateTime: String,
    val status: String = "रिक्वेस्ट भेजी गई"
)

@Composable
fun ServiceRequestScreen(
    selectedService: String = "",
    selectedWorker: String = "",
    onRequestSubmitted: (ServiceRequestData) -> Unit = {}
) {

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

    var requestSubmitted by rememberSaveable {
        mutableStateOf(false)
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

        OutlinedTextField(
            value = location,
            onValueChange = {
                location = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("काम की जगह")
            },
            placeholder = {
                Text("अपना पता या क्षेत्र लिखें")
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
                    dateTime = dateTime
                )

                onRequestSubmitted(request)

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
