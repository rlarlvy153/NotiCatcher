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
    val packageName: String = ""
) {
    fun getIconFilePath() = "${NotiCatcherApp.appContext.filesDir}/$packageName/$sender.png"
}