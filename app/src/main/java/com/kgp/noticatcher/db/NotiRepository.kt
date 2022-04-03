package com.kgp.noticatcher.db

import com.kgp.noticatcher.db.entity.NotiHistory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotiRepository : KoinComponent {
    private val notiDao: NotiDao by inject()

    fun getAllNotiHistory() = notiDao.getAllNotiHistory()

    suspend fun addNotiHistory(history: NotiHistory) = notiDao.addNotiHistory(history)
}