package com.example.radiodaggerforeground.di.modules

import com.example.radiodaggerforeground.RadioApp
import javax.inject.Inject


//функц помогает инжектить зависимости
  inline fun <T> inject(crossinline block : Injector.() ->T): Lazy<T> = lazy { Injector().block() }
// с помощью этой функции вызываетс зависимость!!

class Injector {

    @Inject
    lateinit var mediaPlayer: com.example.radiodaggerforeground.util.MediaPlayer

    init {
        RadioApp.app.daggerComponent.inject(this)
    }
}