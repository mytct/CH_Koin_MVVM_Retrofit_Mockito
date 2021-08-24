package com.mytran.myapplication

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.mytran.myapplication.di.apiModule
import com.mytran.myapplication.di.repositoryModule
import com.mytran.myapplication.di.retrofitModule
import com.mytran.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp: MultiDexApplication() {
    companion object {
        lateinit var instance: MainApp
            private set
    }
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApp)
            modules(listOf(
                //databaseModule,
                viewModelModule,
                apiModule,
                retrofitModule,
                repositoryModule
            ))

        }
        instance = this
    }
}