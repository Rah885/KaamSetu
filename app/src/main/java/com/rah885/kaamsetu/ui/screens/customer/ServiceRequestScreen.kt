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

@Composable
fun ServiceRequestScreen() {

    var service by rememberSaveable {
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
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                requestSubmitted = true
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = service.isNotBlank() &&
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
                text = "✅ आपकी सर्विस रिक्वेस्ट तैयार है।",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "जल्द ही उपलब्ध कामगारों से मिलान किया जाएगा।",
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
