package com.kgp.noticatcher.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kgp.noticatcher.NotiCatcherApp

@Entity(tableName = "NotiHistory")
data class NotiHistory(
    @PrimaryKey(autoGenerate = true)
    @NonNull var id: Long,
    val sender: String = "",
    val message: String = "",
    val roomName: String = "",
    val packageName: String = "",
    val timestamp: Long = 0
) {
    val iconFilePath
        get() = "${NotiCatcherApp.appContext.filesDir.absolutePath}/$id.png"
}