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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rah885.kaamsetu.ui.screens.customer.ServiceRequestData

@Composable
fun NewRequestsScreen(
    serviceRequests: List<ServiceRequestData>,
    onRequestUpdated: (ServiceRequestData) -> Unit
) {

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

        if (serviceRequests.isEmpty()) {

            Text(
                text = "अभी कोई नई सर्विस रिक्वेस्ट नहीं है।",
                style = MaterialTheme.typography.bodyLarge
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = serviceRequests,
                    key = { request -> request.id }
                ) { request ->

                    RequestCard(
                        request = request,
                        onRequestUpdated = onRequestUpdated
                    )
                }
            }
        }
    }
}

@Composable
private fun RequestCard(
    request: ServiceRequestData,
    onRequestUpdated: (ServiceRequestData) -> Unit
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

            Text(
                text = "📌 状态: ${request.status}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (request.status == "काम स्वीकार किया गया") {

                Text(
                    text = "✅ रिक्वेस्ट स्वीकार की गई",
                    style = MaterialTheme.typography.titleMedium
                )

            } else {

                Button(
                    onClick = {

                        val updatedRequest = request.copy(
                            status = "काम स्वीकार किया गया"
                        )

                        onRequestUpdated(updatedRequest)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("काम स्वीकार करें")
                }
            }
        }
    }
}
