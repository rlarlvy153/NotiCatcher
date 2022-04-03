package com.kgp.noticatcher.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotiHistory")
class NotiHistory(
    @PrimaryKey(autoGenerate = true)
    @NonNull var id: Long,

    val title: String = "",
    val message: String = "",

    )