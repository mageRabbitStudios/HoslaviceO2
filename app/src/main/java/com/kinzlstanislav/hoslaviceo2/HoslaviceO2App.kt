package com.kinzlstanislav.hoslaviceo2

import android.app.Application
import com.kinzlstanislav.hoslaviceo2.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class HoslaviceO2App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule).androidContext(this@HoslaviceO2App)
        }
    }

}