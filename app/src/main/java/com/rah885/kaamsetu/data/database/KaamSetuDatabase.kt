package com.rah885.kaamsetu.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AppDataEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KaamSetuDatabase : RoomDatabase() {

    abstract fun appDataDao(): AppDataDao

    companion object {

        @Volatile
        private var INSTANCE: KaamSetuDatabase? = null

        fun getInstance(context: Context): KaamSetuDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    KaamSetuDatabase::class.java,
                    "kaamsetu_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
