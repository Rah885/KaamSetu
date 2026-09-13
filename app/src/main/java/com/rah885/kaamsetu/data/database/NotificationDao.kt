package com.rah885.kaamsetu.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationDao {

    @Insert
    suspend fun insertNotification(notification: NotificationEntity)

    @Query(
        """
        SELECT * FROM notifications
        WHERE recipientId = :recipientId
        ORDER BY createdAt DESC
        """
    )
    suspend fun getNotifications(recipientId: String): List<NotificationEntity>

    @Query(
        """
        SELECT * FROM notifications
        WHERE recipientId = :recipientId
        AND isRead = 0
        ORDER BY createdAt DESC
        """
    )
    suspend fun getUnreadNotifications(recipientId: String): List<NotificationEntity>

    @Query(
        """
        UPDATE notifications
        SET isRead = 1
        WHERE id = :notificationId
        """
    )
    suspend fun markAsRead(notificationId: Long)

    @Query(
        """
        UPDATE notifications
        SET isRead = 1
        WHERE recipientId = :recipientId
        """
    )
    suspend fun markAllAsRead(recipientId: String)

    @Query(
        """
        DELETE FROM notifications
        WHERE id = :notificationId
        """
    )
    suspend fun deleteNotification(notificationId: Long)
}
