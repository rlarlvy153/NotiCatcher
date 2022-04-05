package com.kgp.noticatcher.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kgp.noticatcher.db.entity.NotiHistory

@Database(
    entities = [
        NotiHistory::class
    ],
    version = AppDatabase.VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun notiDao(): NotiDao

}