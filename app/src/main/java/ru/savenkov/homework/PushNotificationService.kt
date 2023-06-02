package ru.savenkov.homework

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class PushNotificationService : Service() {

    private val CHANNEL_ID = "MyChannelId"
    private val NOTIFICATION_ID = 12345

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val delay = intent?.getLongExtra(DELAY_KEY, 0) ?: 0
        val timerTime = intent?.getStringExtra(TIME_ON_TIMER_KEY) ?: "00:00"

        Executors.newSingleThreadScheduledExecutor().apply {
            schedule({
                showNotification(timerTime)
            }, delay, TimeUnit.MILLISECONDS)
            shutdown()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun showNotification(timerTime: String) {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Time on timer")
            .setContentText(timerTime)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    companion object {
        const val DELAY_KEY = "DELAY_KEY"
        const val TIME_ON_TIMER_KEY = "TIME_ON_TIMER_KEY"
    }
}