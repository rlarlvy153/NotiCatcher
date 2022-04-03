package com.kgp.noticatcher

import androidx.lifecycle.ViewModel
import com.kgp.noticatcher.db.NotiRepository
import com.kgp.noticatcher.db.entity.NotiHistory
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    val notiRepository: NotiRepository by inject()

    fun getAllNotiHistory(): Flow<List<NotiHistory>> = notiRepository.getAllNotiHistory()
}