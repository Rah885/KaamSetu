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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class RequestItem(
    val service: String,
    val description: String,
    val location: String,
    val dateTime: String,
    val status: String
)

@Composable
fun MyRequestsScreen() {

    val requests = listOf(
        RequestItem(
            service = "इलेक्ट्रिशियन",
            description = "पंखा खराब है, चेक करवाना है",
            location = "नेहरू नगर",
            dateTime = "आज, शाम 5 बजे",
            status = "कामगार खोजा जा रहा है"
        ),
        RequestItem(
            service = "प्लंबर",
            description = "नल से पानी लीक हो रहा है",
            location = "जुनवानी",
            dateTime = "कल, सुबह 10 बजे",
            status = "कामगार उपलब्ध"
        ),
        RequestItem(
            service = "मैकेनिक",
            description = "बाइक स्टार्ट नहीं हो रही",
            location = "स्मृति नगर",
            dateTime = "12 सितंबर, दोपहर 2 बजे",
            status = "रिक्वेस्ट भेजी गई"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "मेरी रिक्वेस्ट",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "आपकी भेजी हुई सर्विस रिक्वेस्ट",
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

            items(requests) { request ->

                RequestCard(
                    request = request
                )
            }
        }
    }
}

@Composable
private fun RequestCard(
    request: RequestItem
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "🔧 ${request.service}",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "📋 ${request.status}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = request.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "📍 ${request.location}"
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "🕐 ${request.dateTime}"
            )
        }
    }
}
