package com.example.radiodaggerforeground.data

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.radiodaggerforeground.di.modules.inject
import com.example.radiodaggerforeground.util.NotificationUtils
import timber.log.Timber

class RadioService: Service() {     // этот класс помощник при запуске сервиса радио

    private val binder by lazy { RadiobBinder() }
    private val player by inject { mediaPlayer}

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {   // для кликов нотификации
        return super.onStartCommand(intent, flags, startId)
    }

    inner class RadiobBinder : Binder(){
        fun getService() = this@RadioService
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(1, NotificationUtils.createNotification(applicationContext))
    }

    fun play(station: String) {
        player.play(station)
    }

    override fun onBind(p0: Intent?): IBinder? {
        Timber.d("onBind")
        return binder
    }



    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Timber.d("onRebind")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Timber.d("onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {       // !!!!!!
        super.onDestroy()
        player.pause()
        stopSelf()
    }
}