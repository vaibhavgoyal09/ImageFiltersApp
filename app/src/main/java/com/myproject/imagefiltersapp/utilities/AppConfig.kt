package com.myproject.imagefiltersapp.utilities

import android.app.Application
import com.myproject.imagefiltersapp.dependencyinjection.repositoryModule
import com.myproject.imagefiltersapp.dependencyinjection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppConfig : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppConfig)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}