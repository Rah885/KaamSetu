package com.rah885.kaamsetu.ui.screens.worker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkerHelpSupportScreen() {

    val helpItems = listOf(
        "📋 नई सर्विस रिक्वेस्ट" to
                "नई ग्राहक रिक्वेस्ट कैसे देखें और स्वीकार करें।",

        "💰 कमाई और पेमेंट" to
                "अपनी कमाई, पेमेंट और पेमेंट हिस्ट्री से जुड़ी जानकारी।",

        "🔧 काम पूरा करना" to
                "स्वीकार किए गए काम का स्टेटस कैसे अपडेट करें।",

        "👤 प्रोफाइल" to
                "अपनी व्यक्तिगत और काम से जुड़ी जानकारी कैसे बदलें।",

        "🔔 नोटिफिकेशन" to
                "नई रिक्वेस्ट और काम के अपडेट की जानकारी।",

        "🆘 सहायता" to
                "किसी समस्या की स्थिति में कामसेतु सपोर्ट से संपर्क करें।"
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
            text = "कामसेतु इस्तेमाल करने में मदद के लिए नीचे दिए विकल्प देखें।",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        helpItems.forEach { item ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = item.first,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = item.second,
                        modifier = Modifier.padding(top = 6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
