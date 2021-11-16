package com.mutualmobile.kmmblepoc.android

import android.app.Application
import com.mutualmobile.kmmblepoc.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KBPApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KBPApplication)
            modules(commonModule)
        }
    }
}
