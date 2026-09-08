package com.rah885.kaamsetu.ui.screens.worker

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
import androidx.compose.material3.Button
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

private data class ServiceRequestItem(
    val customerName: String,
    val service: String,
    val description: String,
    val location: String,
    val dateTime: String
)

@Composable
fun NewRequestsScreen() {

    val requests = listOf(
        ServiceRequestItem(
            customerName = "राहुल कुमार",
            service = "इलेक्ट्रिशियन",
            description = "पंखा खराब है, चेक करवाना है",
            location = "नेहरू नगर",
            dateTime = "आज, शाम 5 बजे"
        ),
        ServiceRequestItem(
            customerName = "अमित वर्मा",
            service = "प्लंबर",
            description = "नल से पानी लीक हो रहा है",
            location = "जुनवानी",
            dateTime = "कल, सुबह 10 बजे"
        ),
        ServiceRequestItem(
            customerName = "संजय साहू",
            service = "मैकेनिक",
            description = "बाइक स्टार्ट नहीं हो रही",
            location = "स्मृति नगर",
            dateTime = "12 सितंबर, दोपहर 2 बजे"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "नई सर्विस रिक्वेस्ट",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "अपने आसपास की नई रिक्वेस्ट देखें और काम स्वीकार करें।",
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
    request: ServiceRequestItem
) {

    var accepted by rememberSaveable {
        mutableStateOf(false)
    }

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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "👤 ${request.customerName}",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "📋 ${request.service}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
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

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (accepted) {

                Text(
                    text = "✅ रिक्वेस्ट स्वीकार की गई",
                    style = MaterialTheme.typography.titleMedium
                )

            } else {

                Button(
                    onClick = {
                        accepted = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("काम स्वीकार करें")
                }
            }
        }
    }
}
