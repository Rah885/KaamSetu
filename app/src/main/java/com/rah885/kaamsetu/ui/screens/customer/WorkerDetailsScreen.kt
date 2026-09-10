package com.rah885.kaamsetu.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkerDetailsScreen(
    workerName: String,
    serviceName: String,
    rating: String,
    distance: String,
    available: Boolean,
    onRequestClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "कामगार की प्रोफाइल",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = "👤 $workerName",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "🔧 $serviceName",
                    style = MaterialTheme.typography.bodyLarge
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text("⭐ $rating")

                    Text("📍 $distance")
                }

                Text(
                    text = if (available) {
                        "🟢 अभी उपलब्ध"
                    } else {
                        "⚪ अभी व्यस्त"
                    }
                )

                Text(
                    text = "अनुभव: 5+ साल",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "KaamSetu पर सत्यापित कामगार",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = onRequestClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("काम के लिए रिक्वेस्ट भेजें")
        }
    }
}
