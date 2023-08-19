package com.wcp.mediaplayer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MediaPlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}