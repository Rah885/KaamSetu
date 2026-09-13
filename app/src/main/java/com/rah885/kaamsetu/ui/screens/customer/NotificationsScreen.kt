package com.rah885.kaamsetu.ui.screens.customer

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rah885.kaamsetu.data.database.KaamSetuDatabase
import com.rah885.kaamsetu.data.database.NotificationEntity
import com.rah885.kaamsetu.data.database.NotificationRepository
import kotlinx.coroutines.launch

data class NotificationItem(
    val id: Long,
    val title: String,
    val message: String,
    val time: String,
    val icon: String,
    val type: String,
    val referenceId: String?,
    val isRead: Boolean
)

@Composable
fun NotificationsScreen(
    recipientId: String = "",
    onNotificationClick: (NotificationItem) -> Unit = {}
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var notifications by remember {
        mutableStateOf<List<NotificationEntity>>(emptyList())
    }

    LaunchedEffect(recipientId) {

        if (recipientId.isNotBlank()) {

            val database = KaamSetuDatabase.getInstance(context)
            val repository = NotificationRepository(database)

            notifications = repository.getNotifications(recipientId)

        } else {

            notifications = emptyList()
        }
    }

    val notificationItems = notifications.map { notification ->

        NotificationItem(
            id = notification.id,
            title = notification.title,
            message = notification.message,
            time = formatNotificationTime(notification.createdAt),
            icon = getNotificationIcon(notification.type),
            type = notification.type,
            referenceId = notification.referenceId,
            isRead = notification.isRead
        )
    }

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

        if (notificationItems.isEmpty()) {

            Text(
                text = "अभी कोई notification नहीं है।",
                style = MaterialTheme.typography.bodyLarge
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = notificationItems,
                    key = { notification -> notification.id }
                ) { notification ->

                    NotificationCard(
                        notification = notification,
                        onClick = {

                            if (!notification.isRead) {

                                scope.launch {

                                    val database =
                                        KaamSetuDatabase.getInstance(context)

                                    val repository =
                                        NotificationRepository(database)

                                    repository.markAsRead(notification.id)

                                    notifications = notifications.map { item ->

                                        if (item.id == notification.id) {
                                            item.copy(isRead = true)
                                        } else {
                                            item
                                        }
                                    }
                                }
                            }

                            onNotificationClick(notification)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun NotificationCard(
    notification: NotificationItem,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = if (notification.isRead) {
            CardDefaults.cardColors()
        } else {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
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

                if (!notification.isRead) {

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "🆕 नया alert",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

private fun getNotificationIcon(type: String): String {

    return when (type) {

        "NEW_SERVICE_REQUEST" -> "🔔"

        "REQUEST_ACCEPTED" -> "✅"

        "PRICE_SENT" -> "💰"

        "PRICE_ACCEPTED" -> "👍"

        "PRICE_REJECTED" -> "❌"

        "PAYMENT_RECEIVED" -> "💳"

        "WORK_STARTED" -> "🔧"

        "WORK_COMPLETED" -> "✅"

        "RATING_REVIEW" -> "⭐"

        else -> "🔔"
    }
}

private fun formatNotificationTime(createdAt: Long): String {

    val difference = System.currentTimeMillis() - createdAt

    val minute = 60 * 1000L
    val hour = 60 * minute
    val day = 24 * hour

    return when {

        difference < minute ->
            "अभी"

        difference < hour ->
            "${difference / minute} मिनट पहले"

        difference < day ->
            "${difference / hour} घंटे पहले"

        difference < 2 * day ->
            "कल"

        else ->
            "${difference / day} दिन पहले"
    }
}
