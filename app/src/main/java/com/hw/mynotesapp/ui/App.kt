package com.hw.mynotesapp.ui

import android.app.Application
import com.hw.mynotesapp.mvvm.model.di.appModule
import com.hw.mynotesapp.mvvm.model.di.mainModule
import com.hw.mynotesapp.mvvm.model.di.noteModule
import com.hw.mynotesapp.mvvm.model.di.splashModule
import org.koin.android.ext.android.startKoin


class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}