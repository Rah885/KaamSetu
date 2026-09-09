package com.rah885.kaamsetu.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class ServiceItem(
    val name: String,
    val icon: String
)

@Composable
fun ServicesScreen(
    onServiceClick: (String) -> Unit = {}
) {

    val services = listOf(
        ServiceItem("इलेक्ट्रिशियन", "🔧"),
        ServiceItem("प्लंबर", "🚰"),
        ServiceItem("मैकेनिक", "🔩"),
        ServiceItem("पेंटर", "🎨"),
        ServiceItem("वेल्डर", "🔥"),
        ServiceItem("राजमिस्त्री", "🧱"),
        ServiceItem("AC / कूलर रिपेयर", "❄️"),
        ServiceItem("कारपेंटर", "🪚")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "सेवाएँ",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "आपको किस कामगार की जरूरत है?",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(services) { service ->

                ServiceCard(
                    service = service,
                    onClick = {
                        onServiceClick(service.name)
                    }
                )
            }
        }
    }
}

@Composable
private fun ServiceCard(
    service: ServiceItem,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = service.icon,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = service.name,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
