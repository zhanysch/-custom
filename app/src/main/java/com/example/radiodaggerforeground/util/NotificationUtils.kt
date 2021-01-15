package com.example.radiodaggerforeground.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.radiodaggerforeground.BuildConfig
import com.example.radiodaggerforeground.R
import com.example.radiodaggerforeground.data.RadioService

object NotificationUtils {
    // выходит что приложение используется как вкладка на телефоне!!
    private const val CHANNEL_ID = "CHANNEL_ID"

    fun createNotification(context: Context): Notification? {
        createNotificationChannel(context)
        val notificationLayout = RemoteViews(BuildConfig.APPLICATION_ID, R.layout.view_notification)

        val intent = Intent(context, RadioService::class.java)   // для кликов нотификации play
        val pendintIntent = PendingIntent.getService(context,12, intent, 0)
        notificationLayout.setOnClickPendingIntent(R.id.btnPlay,pendintIntent)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_improv)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)

            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        return builder.build()
    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "blabla"
            val descriptionText = "gfdgdfgdgdfg"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}