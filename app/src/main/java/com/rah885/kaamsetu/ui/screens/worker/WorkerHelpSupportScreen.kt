package com.rah885.kaamsetu.ui.screens.worker

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkerHelpSupportScreen() {

    var selectedHelp by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    val helpItems = listOf(
        "📋 नई सर्विस रिक्वेस्ट" to
                "नई ग्राहक सर्विस रिक्वेस्ट यहां से देखें। रिक्वेस्ट को स्वीकार करने के बाद आप उसका स्टेटस और काम की जानकारी देख सकते हैं।",

        "💰 कमाई और पेमेंट" to
                "आपकी कमाई और पेमेंट से जुड़ी जानकारी यहां मिलेगी। भविष्य में यहां पेमेंट हिस्ट्री और कमाई की पूरी जानकारी भी दिखाई जाएगी।",

        "🔧 काम पूरा करना" to
                "स्वीकार किए गए काम को शुरू करें, काम चल रहा है का स्टेटस अपडेट करें और काम पूरा होने पर काम पूरा हुआ का स्टेटस दें।",

        "👤 प्रोफाइल" to
                "अपना नाम, मोबाइल नंबर, सर्विस, पता और शहर जैसी प्रोफाइल जानकारी अपडेट करने के लिए प्रोफाइल एडिट करें।",

        "🔔 नोटिफिकेशन" to
                "नई सर्विस रिक्वेस्ट, काम के अपडेट और दूसरे जरूरी नोटिफिकेशन की जानकारी यहां मिलेगी।",

        "🆘 सहायता" to
                "अगर कामसेतु इस्तेमाल करते समय कोई समस्या आती है तो सहायता सेक्शन में समस्या से जुड़ी जानकारी देखें।"
    )

    if (selectedHelp == null) {

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
                text = "किसी भी विकल्प पर क्लिक करके उसकी जानकारी देखें।",
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
                        .clickable {
                            selectedHelp = item.first
                        }
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

                        Text(
                            text = "जानकारी खोलने के लिए टैप करें →",
                            modifier = Modifier.padding(top = 10.dp),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }

    } else {

        BackHandler {
            selectedHelp = null
        }

        val selectedItem = helpItems.first {
            it.first == selectedHelp
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = selectedItem.first,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = selectedItem.second,
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "← वापस जाने के लिए फोन का Back बटन दबाएं।",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
