package com.kgp.noticatcher.db

import com.kgp.noticatcher.db.entity.NotiHistory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotiRepository : KoinComponent {
    private val notiDao: NotiDao by inject()

    fun getAllNotiHistory() = notiDao.getAllNotiHistory()

    suspend fun addNotiHistory(sender: String, message: String, roomName: String, packageName: String) : NotiHistory{
        val notiHistory = NotiHistory(
            0,
            sender = sender,
            message = message,
            roomName = roomName,
            packageName = packageName,
            timestamp = System.currentTimeMillis()
        )
        notiDao.addNotiHistory(notiHistory)
        return notiHistory
    }
}