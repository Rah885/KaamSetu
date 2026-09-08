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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class WorkerNotificationItem(
    val title: String,
    val message: String,
    val time: String,
    val icon: String
)

@Composable
fun NotificationsScreen() {

    val notifications = listOf(
        WorkerNotificationItem(
            title = "नई सर्विस रिक्वेस्ट",
            message = "आपके आसपास एक नई इलेक्ट्रिशियन सर्विस रिक्वेस्ट उपलब्ध है।",
            time = "5 मिनट पहले",
            icon = "📋"
        ),
        WorkerNotificationItem(
            title = "रिक्वेस्ट स्वीकार हुई",
            message = "ग्राहक ने आपकी सर्विस रिक्वेस्ट स्वीकार कर ली है।",
            time = "1 घंटे पहले",
            icon = "✅"
        ),
        WorkerNotificationItem(
            title = "भुगतान प्राप्त हुआ",
            message = "आपके पूरे किए गए काम का ₹800 भुगतान प्राप्त हुआ।",
            time = "आज",
            icon = "💰"
        ),
        WorkerNotificationItem(
            title = "नई रेटिंग मिली",
            message = "ग्राहक ने आपके काम के लिए 5 ⭐ रेटिंग दी है।",
            time = "कल",
            icon = "⭐"
        ),
        WorkerNotificationItem(
            title = "काम पूरा हुआ",
            message = "आपका मैकेनिक सर्विस जॉब सफलतापूर्वक पूरा हुआ।",
            time = "2 दिन पहले",
            icon = "🔧"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "नोटिफिकेशन",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "आपके काम और रिक्वेस्ट से जुड़े सभी अपडेट",
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

            items(notifications) { notification ->

                WorkerNotificationCard(
                    notification = notification
                )
            }
        }
    }
}

@Composable
private fun WorkerNotificationCard(
    notification: WorkerNotificationItem
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = notification.icon,
                style = MaterialTheme.typography.headlineSmall
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {

                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = notification.time,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
