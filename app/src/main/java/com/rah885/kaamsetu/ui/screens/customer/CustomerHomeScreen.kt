package com.rah885.kaamsetu.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

private data class PopularService(
    val name: String,
    val icon: String
)

@Composable
fun CustomerHomeScreen() {

    val popularServices = listOf(
        PopularService("इलेक्ट्रिशियन", "🔧"),
        PopularService("प्लंबर", "🚰"),
        PopularService("मैकेनिक", "🔩"),
        PopularService("पेंटर", "🎨"),
        PopularService("वेल्डर", "🔥"),
        PopularService("राजमिस्त्री", "🧱"),
        PopularService("AC / कूलर रिपेयर", "❄️"),
        PopularService("कारपेंटर", "🪚")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // =========================
        // पुराना Home Content
        // =========================

        Text(
            text = "कामसेतु",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "आपके काम का सही साथी",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "🔧 इलेक्ट्रिशियन  •  🚰 प्लंबर  •  🔩 मैकेनिक",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "अपने आसपास भरोसेमंद कामगार खोजें",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // =========================
        // नया Home Dashboard
        // =========================

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "नमस्ते 👋",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "कामसेतु में आपका स्वागत है",
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "📍 आपके आसपास भरोसेमंद कामगार उपलब्ध हैं",
                    modifier = Modifier.padding(top = 12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "लोकप्रिय सेवाएँ",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "सभी सेवाएँ →",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(popularServices) { service ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = service.icon,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Text(
                            text = service.name,
                            modifier = Modifier.padding(top = 6.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}
