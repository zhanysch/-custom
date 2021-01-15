package com.example.radiodaggerforeground.di

import com.example.radiodaggerforeground.di.modules.AppModule
import com.example.radiodaggerforeground.di.modules.ExoPlayerModule
import com.example.radiodaggerforeground.di.modules.Injector
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
           AppModule::class,
           ExoPlayerModule::class]
)
interface RadioComponent {
    fun inject(injector: Injector)
}