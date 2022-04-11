package com.kgp.noticatcher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kgp.noticatcher.db.NotiRepository
import com.kgp.noticatcher.db.entity.NotiHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    private val notiRepository: NotiRepository by inject()

    val mData = MutableLiveData<List<ChatRoom>>()

    private fun getAllNotiHistory(): Flow<List<NotiHistory>> = notiRepository.getAllNotiHistory()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            getAllNotiHistory().collect {
                var temp = ArrayList<ChatRoom>()
                for (row in it) {
                    temp.add(ChatRoom(user = row.sender, message = row.message))
                }
                mData.postValue(temp)
            }
        }
    }

}