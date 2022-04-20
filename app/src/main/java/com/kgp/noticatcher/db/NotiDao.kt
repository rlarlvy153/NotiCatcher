package com.kgp.noticatcher.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kgp.noticatcher.db.entity.NotiHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface NotiDao {
    @Query("SELECT * FROM NotiHistory ORDER BY id")
    fun getAllNotiHistoryAsFlow(): Flow<List<NotiHistory>>

    @Query("SELECT * FROM NotiHistory WHERE id=:id")
    fun findNotiHistoryById(id: Long): NotiHistory?

    @Insert
    suspend fun addNotiHistory(history: NotiHistory): Long
}