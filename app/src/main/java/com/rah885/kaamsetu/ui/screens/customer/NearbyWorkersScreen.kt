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

private data class WorkerItem(
    val name: String,
    val service: String,
    val rating: String,
    val distance: String,
    val available: Boolean
)

@Composable
fun NearbyWorkersScreen() {

    val workers = listOf(
        WorkerItem(
            name = "रमेश कुमार",
            service = "इलेक्ट्रिशियन",
            rating = "4.8",
            distance = "1.2 km",
            available = true
        ),
        WorkerItem(
            name = "सुरेश साहू",
            service = "प्लंबर",
            rating = "4.7",
            distance = "2.1 km",
            available = true
        ),
        WorkerItem(
            name = "अजय वर्मा",
            service = "मैकेनिक",
            rating = "4.6",
            distance = "2.8 km",
            available = false
        ),
        WorkerItem(
            name = "मोहन यादव",
            service = "पेंटर",
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
            text = "आसपास के कामगार",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "अपने आसपास उपलब्ध और भरोसेमंद कामगार खोजें",
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

                WorkerCard(
                    worker = worker
                )
            }
        }
    }
}

@Composable
private fun WorkerCard(
    worker: WorkerItem
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
