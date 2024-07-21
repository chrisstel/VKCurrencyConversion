package com.example.vkcurrencyconversion

import android.app.Application
import android.content.res.Resources
import com.example.vkcurrencyconversion.di.dataModule
import com.example.vkcurrencyconversion.di.domainModule
import com.example.vkcurrencyconversion.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    companion object {
        lateinit var res: Resources
    }

    override fun onCreate() {
        super.onCreate()

        res = resources

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(listOf(uiModule, domainModule, dataModule))
        }
    }
}