package com.example.gpaymotion

import android.app.Application
import com.example.gpaymotion.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GpayMotionApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@GpayMotionApplication)
            modules(appModule)
        }
    }
}