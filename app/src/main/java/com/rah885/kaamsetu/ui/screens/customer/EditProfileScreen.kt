package com.rah885.kaamsetu.ui.screens.customer

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
fun EditProfileScreen() {

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var mobile by rememberSaveable {
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
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "प्रोफाइल एडिट करें",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "अपनी जानकारी अपडेट करें",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
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
                Text("पूरा नाम")
            },
            placeholder = {
                Text("अपना नाम लिखें")
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = mobile,
            onValueChange = {
                mobile = it
                saved = false
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
            value = address,
            onValueChange = {
                address = it
                saved = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("पता")
            },
            placeholder = {
                Text("अपना पूरा पता लिखें")
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
            placeholder = {
                Text("जैसे भिलाई, दुर्ग")
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
                    address.isNotBlank() &&
                    city.isNotBlank()
        ) {
            Text("जानकारी सेव करें")
        }

        if (saved) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "✅ प्रोफाइल सफलतापूर्वक अपडेट हो गई।",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
