package com.rah885.kaamsetu.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class HelpItem(
    val icon: String,
    val title: String,
    val description: String
)

@Composable
fun HelpSupportScreen() {

    val helpItems = listOf(
        HelpItem(
            icon = "❓",
            title = "कामसेतु कैसे काम करता है?",
            description = "अपनी जरूरत की सेवा चुनें, रिक्वेस्ट भेजें और अपने आसपास उपलब्ध कामगार से संपर्क करें।"
        ),
        HelpItem(
            icon = "📋",
            title = "सर्विस रिक्वेस्ट कैसे भेजें?",
            description = "सेवा का नाम, काम की जानकारी और लोकेशन भरकर रिक्वेस्ट भेजें।"
        ),
        HelpItem(
            icon = "👷",
            title = "कामगार कैसे चुनें?",
            description = "कामगार की रेटिंग, दूरी और उपलब्धता देखकर अपनी जरूरत के अनुसार कामगार चुनें।"
        ),
        HelpItem(
            icon = "⭐",
            title = "रेटिंग और रिव्यू",
            description = "काम पूरा होने के बाद आप कामगार को रेटिंग और अपना अनुभव साझा कर सकते हैं।"
        ),
        HelpItem(
            icon = "🔒",
            title = "मेरी जानकारी सुरक्षित है?",
            description = "अपनी व्यक्तिगत जानकारी केवल जरूरी जगहों पर साझा करें और किसी अनजान व्यक्ति को OTP या पासवर्ड न दें।"
        ),
        HelpItem(
            icon = "📞",
            title = "सपोर्ट से संपर्क करें",
            description = "किसी समस्या की स्थिति में कामसेतु के सपोर्ट विकल्प का उपयोग करें।"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "मदद और सपोर्ट",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "कामसेतु इस्तेमाल करने में किसी भी मदद के लिए नीचे दिए विकल्प देखें।",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(helpItems) { item ->

                HelpCard(
                    item = item
                )
            }
        }
    }
}

@Composable
private fun HelpCard(
    item: HelpItem
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = item.icon,
                style = MaterialTheme.typography.headlineSmall
            )

            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
