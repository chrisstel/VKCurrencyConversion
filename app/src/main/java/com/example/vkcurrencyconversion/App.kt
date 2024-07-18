package com.example.vkcurrencyconversion

import android.app.Application
import com.example.vkcurrencyconversion.di.dataModule
import com.example.vkcurrencyconversion.di.domainModule
import com.example.vkcurrencyconversion.di.uiModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            modules(listOf(uiModule, domainModule, dataModule))
        }
    }
}