package com.rah885.kaamsetu.data.database

class NotificationRepository(
    private val database: KaamSetuDatabase
) {

    private val notificationDao = database.notificationDao()

    suspend fun createNotification(
        recipientId: String,
        senderId: String,
        recipientRole: String,
        type: String,
        title: String,
        message: String,
        referenceId: String? = null
    ) {
        notificationDao.insertNotification(
            NotificationEntity(
                recipientId = recipientId,
                senderId = senderId,
                recipientRole = recipientRole,
                type = type,
                title = title,
                message = message,
                referenceId = referenceId,
                isRead = false
            )
        )
    }

    suspend fun getNotifications(
        recipientId: String
    ): List<NotificationEntity> {
        return notificationDao.getNotifications(recipientId)
    }

    suspend fun getUnreadNotifications(
        recipientId: String
    ): List<NotificationEntity> {
        return notificationDao.getUnreadNotifications(recipientId)
    }

    suspend fun markAsRead(
        notificationId: Long
    ) {
        notificationDao.markAsRead(notificationId)
    }

    suspend fun markAllAsRead(
        recipientId: String
    ) {
        notificationDao.markAllAsRead(recipientId)
    }

    suspend fun deleteNotification(
        notificationId: Long
    ) {
        notificationDao.deleteNotification(notificationId)
    }
}
