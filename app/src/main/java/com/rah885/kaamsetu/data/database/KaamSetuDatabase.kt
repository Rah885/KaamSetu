package com.rah885.kaamsetu.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        AppDataEntity::class,
        NotificationEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class KaamSetuDatabase : RoomDatabase() {

    abstract fun appDataDao(): AppDataDao

    abstract fun notificationDao(): NotificationDao

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS notifications (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        recipientId TEXT NOT NULL,
                        senderId TEXT NOT NULL,
                        recipientRole TEXT NOT NULL,
                        type TEXT NOT NULL,
                        title TEXT NOT NULL,
                        message TEXT NOT NULL,
                        referenceId TEXT,
                        isRead INTEGER NOT NULL,
                        createdAt INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        @Volatile
        private var INSTANCE: KaamSetuDatabase? = null

        fun getInstance(context: Context): KaamSetuDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    KaamSetuDatabase::class.java,
                    "kaamsetu_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}
