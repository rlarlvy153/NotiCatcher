package com.kgp.noticatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kgp.noticatcher.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class MainActivity : AppCompatActivity(), KoinComponent {
    lateinit var binding: ActivityMainBinding
//    val receiver = MyBroadcastReceiver()

    val viewModel: MainViewModel by inject() //viewmodel 제대로 inject 되는지 확인필요

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.getAllNotiHistory().collect {
                // noti history 테이블에 변화가생기면
                // 디비에 있는 모든 row가 꽂힌다.

                for (row in it) {
                    Timber.d("title ${row.title}, message ${row.title}")
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        //test
//        val filter = IntentFilter()
//        filter.addAction("com.kgp.noticatcher.TEST")
//        registerReceiver(receiver, filter)

    }

//    inner class MyBroadcastReceiver: BroadcastReceiver(){
//        override fun onReceive(context: Context?, intent: Intent?) {
//            val small = intent?.getParcelableExtra<Icon>("small")
//            val large = intent?.getParcelableExtra<Icon>("large")
//
//
//
//            val smallD = small?.loadDrawable(this@MainActivity)
//            val largeD = large?.loadDrawable(this@MainActivity)
//
//            Log.d("kgpp","small " + smallD)
//            Log.d("kgpp","large " + largeD)
//
//            binding.image.setImageDrawable(large!!.loadDrawable(this@MainActivity))
//            Log.d("kgpp","hash " + large.toString())
//        }
//    }


}