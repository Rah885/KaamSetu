package com.rah885.kaamsetu.ui.screens.worker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun WorkerDashboardScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "कामगार डैशबोर्ड",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "आपके काम और रिक्वेस्ट का सारांश",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                title = "नई रिक्वेस्ट",
                value = "3",
                icon = "📋",
                modifier = Modifier.weight(1f)
            )

            DashboardCard(
                title = "मेरे काम",
                value = "5",
                icon = "🔧",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                title = "कमाई",
                value = "₹2,500",
                icon = "💰",
                modifier = Modifier.weight(1f)
            )

            DashboardCard(
                title = "रेटिंग",
                value = "4.8 ⭐",
                icon = "🏆",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                Text(
                    text = "👷 आपका काम",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "अपने आसपास की नई सर्विस रिक्वेस्ट देखें और अपनी सुविधा के अनुसार काम स्वीकार करें।",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun DashboardCard(
    title: String,
    value: String,
    icon: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = icon,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = title,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
