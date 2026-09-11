package com.rah885.kaamsetu.ui.screens.worker

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun WorkerProfileScreen(
    name: String = "कामगार",
    mobile: String = "",
    service: String = "इलेक्ट्रिशियन • प्लंबर",
    address: String = "",
    city: String = "",
    photoUri: String? = null,
    onEditProfileClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val context = LocalContext.current

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
            text = "कामगार प्रोफाइल",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (profileBitmap != null) {
                    Image(
                        bitmap = profileBitmap.asImageBitmap(),
                        contentDescription = "प्रोफाइल फोटो",
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Text(
                        text = "👷",
                        style = MaterialTheme.typography.displaySmall
                    )
                }

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = name.ifBlank { "कामगार" },
                    style = MaterialTheme.typography.titleLarge
                )

                if (mobile.isNotBlank()) {
                    Text(
                        text = "📱 $mobile",
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Text(
                    text = service.ifBlank { "सर्विस उपलब्ध" },
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )

                if (address.isNotBlank()) {
                    Text(
                        text = "📍 $address",
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                if (city.isNotBlank()) {
                    Text(
                        text = "🏙️ $city",
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "⭐ 4.8 • 🔧 18 काम पूरे",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        ProfileOption(
            title = "प्रोफाइल एडिट करें",
            icon = "✏️",
            onClick = onEditProfileClick
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        ProfileOption(
            title = "सेटिंग्स",
            icon = "⚙️",
            onClick = onSettingsClick
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        ProfileOption(
            title = "मदद और सपोर्ट",
            icon = "❓",
            onClick = onHelpClick
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedButton(
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🚪 लॉगआउट")
        }
    }
}

@Composable
private fun ProfileOption(
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(icon)

            Text(
                text = title,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}
