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

private data class ServiceWorkerItem(
    val name: String,
    val service: String,
    val rating: String,
    val distance: String,
    val available: Boolean
)

@Composable
fun ServiceWorkersScreen(
    serviceName: String,
    onWorkerClick: (
        workerName: String,
        serviceName: String,
        rating: String,
        distance: String,
        available: Boolean
    ) -> Unit = { _, _, _, _, _ -> }
) {

    val workers = listOf(
        ServiceWorkerItem(
            name = "रमेश कुमार",
            service = serviceName,
            rating = "4.8",
            distance = "1.2 km",
            available = true
        ),
        ServiceWorkerItem(
            name = "सुरेश साहू",
            service = serviceName,
            rating = "4.7",
            distance = "2.1 km",
            available = true
        ),
        ServiceWorkerItem(
            name = "अजय वर्मा",
            service = serviceName,
            rating = "4.6",
            distance = "2.8 km",
            available = false
        ),
        ServiceWorkerItem(
            name = "मोहन यादव",
            service = serviceName,
            rating = "4.9",
            distance = "3.4 km",
            available = true
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = serviceName,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "इस सेवा के आसपास उपलब्ध कामगार",
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

            items(workers) { worker ->

                ServiceWorkerCard(
                    worker = worker,
                    onClick = {
                        onWorkerClick(
                            worker.name,
                            worker.service,
                            worker.rating,
                            worker.distance,
                            worker.available
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ServiceWorkerCard(
    worker: ServiceWorkerItem,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
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
                    text = "👤 ${worker.name}",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = if (worker.available) {
                        "🟢 उपलब्ध"
                    } else {
                        "⚪ व्यस्त"
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "🔧 ${worker.service}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "⭐ ${worker.rating}"
                )

                Text(
                    text = "📍 ${worker.distance}"
                )
            }
        }
    }
}
