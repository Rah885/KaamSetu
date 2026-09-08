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

private data class NotificationItem(
    val title: String,
    val message: String,
    val time: String,
    val icon: String
)

@Composable
fun NotificationsScreen() {

    val notifications = listOf(
        NotificationItem(
            title = "कामगार उपलब्ध है",
            message = "आपकी इलेक्ट्रिशियन रिक्वेस्ट के लिए एक कामगार उपलब्ध है।",
            time = "5 मिनट पहले",
            icon = "🔧"
        ),
        NotificationItem(
            title = "रिक्वेस्ट अपडेट",
            message = "आपकी प्लंबर रिक्वेस्ट पर कामगार ने प्रतिक्रिया दी है।",
            time = "1 घंटे पहले",
            icon = "🚰"
        ),
        NotificationItem(
            title = "रिक्वेस्ट भेजी गई",
            message = "आपकी मैकेनिक सर्विस रिक्वेस्ट सफलतापूर्वक भेज दी गई है।",
            time = "कल",
            icon = "📋"
        ),
        NotificationItem(
            title = "कामसेतु में आपका स्वागत है",
            message = "अब आप अपने आसपास भरोसेमंद कामगार आसानी से खोज सकते हैं।",
            time = "2 दिन पहले",
            icon = "🎉"
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
            text = "आपकी सर्विस और रिक्वेस्ट से जुड़े अपडेट",
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

                NotificationCard(
                    notification = notification
                )
            }
        }
    }
}

@Composable
private fun NotificationCard(
    notification: NotificationItem
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
                    .weight(1f)
            ) {

                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
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
