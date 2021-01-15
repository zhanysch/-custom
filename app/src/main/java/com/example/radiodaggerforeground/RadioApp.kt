package com.example.radiodaggerforeground

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.example.radiodaggerforeground.di.DaggerRadioComponent
import com.example.radiodaggerforeground.di.RadioComponent
import com.example.radiodaggerforeground.di.modules.AppModule
import timber.log.Timber

class RadioApp: Application() {

    lateinit var daggerComponent: RadioComponent

    override fun onCreate() {
        super.onCreate()
        app = this
        daggerComponent = DaggerRadioComponent
            .builder()
            .appModule(AppModule(this)).build()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

    }

    companion object{
        lateinit var app : RadioApp
    }
}
