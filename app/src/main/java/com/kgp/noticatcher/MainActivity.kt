package com.kgp.noticatcher

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kgp.noticatcher.databinding.ActivityMainBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainActivity : AppCompatActivity(), KoinComponent {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject() //viewmodel 제대로 inject 되는지 확인필요

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        checkPermission()

    }

    private fun initRecyclerView() {
        val adapter = RoomAdapter()
        binding.RoomRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.RoomRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결

        viewModel.roomData.observe(this) {
            adapter.dataList = it
            adapter.notifyDataSetChanged()
        }
    }

    private fun checkPermission() {
        if (!permissionGranted()) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)

            startActivity(intent)
        }
    }

    private fun permissionGranted(): Boolean {
        val sets = NotificationManagerCompat.getEnabledListenerPackages(this)
        return sets.contains(packageName)
    }
}