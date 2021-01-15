package com.example.radiodaggerforeground.di.modules

import android.content.Context
import com.example.radiodaggerforeground.util.MediaPlayer
import com.example.radiodaggerforeground.util.MediaPlayerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExoPlayerModule {
    @Provides
    @Singleton
    fun provideExoPlayer(context: Context): MediaPlayer{
        return MediaPlayerImpl(context)
    }
}