package com.rah885.kaamsetu.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDataDao {

    @Query("SELECT * FROM app_data WHERE `key` = :key LIMIT 1")
    suspend fun get(key: String): AppDataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(data: AppDataEntity)

    @Delete
    suspend fun delete(data: AppDataEntity)

    @Query("DELETE FROM app_data WHERE `key` = :key")
    suspend fun deleteByKey(key: String)

    @Query("DELETE FROM app_data")
    suspend fun clearAll()
}
