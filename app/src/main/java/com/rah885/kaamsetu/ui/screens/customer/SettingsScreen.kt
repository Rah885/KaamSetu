package com.rah885.kaamsetu.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {

    var notificationsEnabled by rememberSaveable {
        mutableStateOf(true)
    }

    var locationEnabled by rememberSaveable {
        mutableStateOf(true)
    }

    var languageHindi by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "सेटिंग्स",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "कामसेतु ऐप की सेटिंग्स को अपनी जरूरत के अनुसार बदलें",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        SettingSwitchRow(
            title = "नोटिफिकेशन",
            description = "नई रिक्वेस्ट और कामगार के अपडेट पाएं",
            checked = notificationsEnabled,
            onCheckedChange = {
                notificationsEnabled = it
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp)
        )

        SettingSwitchRow(
            title = "लोकेशन",
            description = "आसपास के कामगार खोजने के लिए लोकेशन का उपयोग करें",
            checked = locationEnabled,
            onCheckedChange = {
                locationEnabled = it
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp)
        )

        SettingSwitchRow(
            title = "हिंदी भाषा",
            description = "ऐप की भाषा हिंदी रखें",
            checked = languageHindi,
            onCheckedChange = {
                languageHindi = it
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        SettingInfoRow(
            title = "🔒 प्राइवेसी",
            description = "आपकी व्यक्तिगत जानकारी की सुरक्षा"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        SettingInfoRow(
            title = "ℹ️ कामसेतु के बारे में",
            description = "कामसेतु — आपके काम का सही साथी"
        )
    }
}

@Composable
private fun SettingSwitchRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = description,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
private fun SettingInfoRow(
    title: String,
    description: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = description,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
