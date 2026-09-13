package com.rah885.kaamsetu.ui.screens.worker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rah885.kaamsetu.data.database.KaamSetuDatabase
import com.rah885.kaamsetu.data.database.NotificationEntity
import com.rah885.kaamsetu.data.database.NotificationRepository

@Composable
fun WorkerNotificationsScreen(
    workerId: String,
    onNotificationClick: (NotificationEntity) -> Unit = {}
) {
    var notifications by remember {
        mutableStateOf<List<NotificationEntity>>(emptyList())
    }

    LaunchedEffect(workerId) {
        if (workerId.isNotBlank()) {
            val database = KaamSetuDatabase.getInstance(
                androidx.compose.ui.platform.LocalContext.current
            )

            val repository = NotificationRepository(database)

            notifications = repository.getNotifications(workerId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "🔔 Alerts",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        if (notifications.isEmpty()) {

            Text(
                text = "अभी कोई नया alert नहीं है।",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = notifications,
                    key = { it.id }
                ) { notification ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onNotificationClick(notification)
                            }
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = notification.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = notification.message,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 6.dp)
                            )

                            Text(
                                text = if (notification.isRead) {
                                    "पढ़ा गया"
                                } else {
                                    "नया"
                                },
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
