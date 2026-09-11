package com.rah885.kaamsetu.ui.screens.worker

import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun WorkerEditProfileScreen(
    initialName: String,
    initialMobile: String,
    initialService: String,
    initialAddress: String,
    initialCity: String,
    initialPhotoUri: String?,
    onSave: (
        name: String,
        mobile: String,
        service: String,
        address: String,
        city: String,
        photoUri: String?
    ) -> Unit
) {
    var name by rememberSaveable {
        mutableStateOf(initialName)
    }

    var mobile by rememberSaveable {
        mutableStateOf(initialMobile)
    }

    var service by rememberSaveable {
        mutableStateOf(initialService)
    }

    var address by rememberSaveable {
        mutableStateOf(initialAddress)
    }

    var city by rememberSaveable {
        mutableStateOf(initialCity)
    }

    var photoUri by rememberSaveable {
        mutableStateOf(initialPhotoUri)
    }

    var saved by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val photoPicker =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            if (uri != null) {
                photoUri = uri.toString()
                saved = false
            }
        }

    val profileBitmap = remember(photoUri) {
        if (photoUri.isNullOrBlank()) {
            null
        } else {
            try {
                context.contentResolver
                    .openInputStream(
                        android.net.Uri.parse(photoUri)
                    )
                    ?.use { inputStream ->
                        BitmapFactory.decodeStream(inputStream)
                    }
            } catch (e: Exception) {
                null
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "प्रोफाइल एडिट करें",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        if (profileBitmap != null) {

            Image(
                bitmap = profileBitmap.asImageBitmap(),
                contentDescription = "प्रोफाइल फोटो",
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .clickable {
                        photoPicker.launch("image/*")
                    }
            )

        } else {

            Column(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .clickable {
                        photoPicker.launch("image/*")
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "👤",
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "फोटो बदलने के लिए फोटो पर टैप करें",
            style = MaterialTheme.typography.bodyMedium
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
                val digitsOnly = it.filter { char ->
                    char.isDigit()
                }

                if (digitsOnly.length <= 10) {
                    mobile = digitsOnly
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
                onSave(
                    name,
                    mobile,
                    service,
                    address,
                    city,
                    photoUri
                )

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
