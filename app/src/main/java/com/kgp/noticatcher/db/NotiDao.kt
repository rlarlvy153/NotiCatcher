package com.kgp.noticatcher.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kgp.noticatcher.db.entity.NotiHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface NotiDao {
    @Query("SELECT * FROM NotiHistory ORDER BY id")
    fun getAllNotiHistory(): Flow<List<NotiHistory>>

    @Insert
    suspend fun addNotiHistory(history: NotiHistory)
}