package com.kgp.noticatcher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kgp.noticatcher.db.NotiRepository
import com.kgp.noticatcher.db.entity.NotiHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException

class MainViewModel : ViewModel(), KoinComponent {
    private val notiRepository: NotiRepository by inject()

    val roomData = MutableLiveData<List<ChatRoom>>()

    private fun getAllNotiHistoryAsFlow(): Flow<List<NotiHistory>> = notiRepository.getAllNotiHistoryAsFlow()

    init {
        subscribeData()
    }

    private fun subscribeData() {
        viewModelScope.launch {
            getAllNotiHistoryAsFlow().collect {
                val chatroomDatas = it.map { notiHistory ->
                    ChatRoom(
                        user = notiHistory.sender,
                        message = notiHistory.message,
                        imageFilePath = notiHistory.iconFilePath
                    )
                }
                roomData.postValue(chatroomDatas)
            }
        }
    }

    //icon 파일은 있는데 DB에 없으면 제거
    fun clearUselessIcon() {
        viewModelScope.launch(Dispatchers.IO) {
            NotiCatcherApp.appContext.filesDir.listFiles()?.let { files ->
                files
                    .filter {
                        it.extension == "png"
                    }
                    .filter { //쿼리 더 최적화 될듯
                        val id = it.nameWithoutExtension.toLongOrNull()
                        if (id != null) {
                            notiRepository.existInNoti(id).not()
                        } else {
                            false
                        }
                    }.forEach {
                        try {
                            it.delete()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
            }
        }
    }
}