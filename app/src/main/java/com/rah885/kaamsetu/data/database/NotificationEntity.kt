package com.rah885.kaamsetu.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // जिस user को notification दिखना है
    val recipientId: String,

    // जिसने action किया
    val senderId: String,

    // CUSTOMER या WORKER
    val recipientRole: String,

    // Alert किस प्रकार का है
    val type: String,

    val title: String,

    val message: String,

    // संबंधित service request / job
    val referenceId: String?,

    // false = नया notification, true = पढ़ लिया
    val isRead: Boolean = false,

    // Notification बनने का समय
    val createdAt: Long = System.currentTimeMillis()
)
