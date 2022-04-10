package com.kgp.noticatcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kgp.noticatcher.databinding.ActivityMainBinding
import com.kgp.noticatcher.db.entity.NotiHistory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber


class MainActivity : AppCompatActivity(), KoinComponent {
    lateinit var binding: ActivityMainBinding

    private val mData = mutableListOf<NotiHistory>()


    private val viewModel: MainViewModel by inject() //viewmodel 제대로 inject 되는지 확인필요

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RoomAdapter() //어댑터 객체 만듦
        adapter.dataList = mData //데이터 넣어줌
        binding.recyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결

        lifecycleScope.launch {
            viewModel.getAllNotiHistory().collect {
                // noti history 테이블에 변화가생기면
                // 디비에 있는 모든 row가 꽂힌다.
                mData.clear()
                for (row in it) {
                    Timber.d("title ${row.sender}, message ${row.sender}")
                }
                adapter.notifyDataSetChanged()
            }
        }

        if (!permissionGranted()) {
            val intent = Intent(
                "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
            )
            startActivity(intent)
        }
    }

    private fun permissionGranted(): Boolean {
        val sets = NotificationManagerCompat.getEnabledListenerPackages(this)
        return sets.contains(packageName)
    }
}