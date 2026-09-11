package com.rah885.kaamsetu.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_data")
data class AppDataEntity(
    @PrimaryKey
    val key: String,
    val value: String
)
