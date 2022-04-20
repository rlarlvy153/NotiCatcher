package com.kgp.noticatcher

import android.app.Notification
import android.graphics.Bitmap
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.kgp.noticatcher.db.NotiRepository
import com.kgp.noticatcher.db.entity.NotiHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream


class NotiListenerService : NotificationListenerService(), KoinComponent {
    private val notoRepository: NotiRepository by inject()

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val noti = sbn.notification
        val extras = noti.extras

        if (sbn.packageName != "com.kakao.talk")
            return

        if (noti.tickerText == null)
            return

        Log.d("kgpp", "================")
        Log.d("kgpp", "패키지 " + sbn.packageName)
        Log.d("kgpp", "보낸이 : 메시지 " + noti.tickerText)
        Log.d("kgpp", "텍스트 " + extras.getCharSequence(Notification.EXTRA_TEXT))
        Log.d("kgpp", "보낸이 " + extras.getCharSequence(Notification.EXTRA_TITLE)) //비어서 들어오는경우 있는지 체크
        Log.d("kgpp", "시간? " + extras.getCharSequence(Notification.EXTRA_SUB_TEXT))
        //TODO 단톡방 테스트
        val sender = extras.getString(Notification.EXTRA_TITLE) ?: ""
        val message = extras.getString(Notification.EXTRA_TEXT) ?: ""
        val roomName = extras.getString(Notification.EXTRA_SUB_TEXT) ?: ""
        val packageName = sbn.packageName

        //TODO drawable 처리 어떻게 할지?
        val smallIcon = noti.smallIcon
        val largeIcon = noti.getLargeIcon()
        Log.d("kgpp", "아이콘 " + largeIcon.toString())

        val icon = largeIcon ?: smallIcon


        //TODO viewmodel, repository등으로 분리
        CoroutineScope(Dispatchers.IO).launch {
            val notiHistory = notoRepository.addNotiHistory(sender, message, roomName, packageName)

            val parentDir = "$filesDir/$packageName"
            val parentsFile = File(parentDir)
            if (!parentsFile.exists()) {
                parentsFile.mkdirs()
            }

            if (icon != null) {
                val iconDrawable = icon.loadDrawable(this@NotiListenerService)
                iconDrawable?.let {
                    try {
                        val bitmap = it.toBitmap()
                        val fileName = notiHistory.iconFilePath
                        val iconFile = File(fileName)

                        if (iconFile.exists()) {
                            iconFile.delete()
                        }
                        iconFile.createNewFile()

                        val outputStream = FileOutputStream(iconFile)
                        val bufferedFileOutputStream = BufferedOutputStream(outputStream)
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedFileOutputStream)

                        bufferedFileOutputStream.close()
                        outputStream.close()

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }


//        val intent = Intent("com.kgp.noticatcher.TEST")
//        intent.putExtra("small", smallIcon)
//        intent.putExtra("large", largeIcon)
//        sendBroadcast(intent)

    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
//        Log.i(TAG, "********** onNOtificationRemoved")
//        Log.i(TAG, "ID :" + sbn.id + "\t" + sbn.notification.tickerText + "\t" + sbn.packageName)
//        val i = Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE")
//        i.putExtra(
//            "notification_event", """
//     onNotificationRemoved :${sbn.packageName}
//
//     """.trimIndent()
//        )

    }


}