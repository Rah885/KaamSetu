package com.rah885.kaamsetu.ui.screens.worker

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
fun WorkerSettingsScreen() {

    var notificationsEnabled by rememberSaveable {
        mutableStateOf(true)
    }

    var locationEnabled by rememberSaveable {
        mutableStateOf(true)
    }

    var availableForWork by rememberSaveable {
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
            text = "कामगार सेटिंग्स",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "कामसेतु पर अपने काम और प्रोफाइल की सेटिंग्स बदलें",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        WorkerSettingSwitchRow(
            title = "नोटिफिकेशन",
            description = "नई सर्विस रिक्वेस्ट और काम के अपडेट पाएं",
            checked = notificationsEnabled,
            onCheckedChange = {
                notificationsEnabled = it
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp)
        )

        WorkerSettingSwitchRow(
            title = "लोकेशन",
            description = "अपने आसपास की सर्विस रिक्वेस्ट खोजने के लिए लोकेशन का उपयोग करें",
            checked = locationEnabled,
            onCheckedChange = {
                locationEnabled = it
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp)
        )

        WorkerSettingSwitchRow(
            title = "काम के लिए उपलब्ध",
            description = "उपलब्ध होने पर ग्राहक आपको सर्विस के लिए चुन सकते हैं",
            checked = availableForWork,
            onCheckedChange = {
                availableForWork = it
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp)
        )

        WorkerSettingSwitchRow(
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

        WorkerSettingInfoRow(
            title = "🔒 प्राइवेसी",
            description = "आपकी व्यक्तिगत जानकारी की सुरक्षा"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        WorkerSettingInfoRow(
            title = "ℹ️ कामसेतु के बारे में",
            description = "कामसेतु — कामगारों को ग्राहकों से जोड़ने वाला प्लेटफॉर्म"
        )
    }
}

@Composable
private fun WorkerSettingSwitchRow(
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
private fun WorkerSettingInfoRow(
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
