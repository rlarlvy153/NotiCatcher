package com.kgp.noticatcher

import android.app.Notification
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.kgp.noticatcher.db.NotiRepository
import com.kgp.noticatcher.db.entity.NotiHistory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class NotiListenerService : NotificationListenerService(), KoinComponent {
    val notoRepository: NotiRepository by inject()

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val noti = sbn.notification
        val extras = noti.extras

        if (sbn.packageName != "com.kakao.talk")
            return

        if (noti.tickerText == null)
            return

        Log.d("kgpp", "================")
        Log.d("kgpp", "패키지 " + sbn.packageName)
        Log.d("kgpp", "보낸이 " + noti.tickerText)
        Log.d("kgpp", "텍스트 " + extras.getCharSequence(Notification.EXTRA_TEXT))


        //TODO drawable 처리 어떻게 할지?
        val smallIcon = noti.smallIcon
        val largeIcon = noti.getLargeIcon()
        Log.d("kgpp", "아이콘 " + largeIcon.toString())

        GlobalScope.launch {
            val notiHistory = NotiHistory(0, noti.tickerText.toString(), extras.getCharSequence(Notification.EXTRA_TEXT).toString())
            notoRepository.addNotiHistory(notiHistory)
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