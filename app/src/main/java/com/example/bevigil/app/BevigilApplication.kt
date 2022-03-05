package com.example.bevigil.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BevigilApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }
}