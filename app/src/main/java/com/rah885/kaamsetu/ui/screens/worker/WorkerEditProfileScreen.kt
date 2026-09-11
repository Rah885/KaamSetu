package com.rah885.kaamsetu.ui.screens.worker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun WorkerEditProfileScreen() {

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var mobile by rememberSaveable {
        mutableStateOf("")
    }

    var service by rememberSaveable {
        mutableStateOf("")
    }

    var address by rememberSaveable {
        mutableStateOf("")
    }

    var city by rememberSaveable {
        mutableStateOf("")
    }

    var saved by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "प्रोफाइल एडिट करें",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                saved = false
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
                    saved = false
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
            value = service,
            onValueChange = {
                service = it
                saved = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("आप कौन-सा काम करते हैं?")
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
            value = address,
            onValueChange = {
                address = it
                saved = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("पता")
            },
            minLines = 2
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = city,
            onValueChange = {
                city = it
                saved = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("शहर / क्षेत्र")
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                saved = true
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank() &&
                    mobile.length == 10 &&
                    service.isNotBlank() &&
                    address.isNotBlank() &&
                    city.isNotBlank()
        ) {
            Text("प्रोफाइल सेव करें")
        }

        if (saved) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "✅ आपकी प्रोफाइल सेव हो गई है।",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
