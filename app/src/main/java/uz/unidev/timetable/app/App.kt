package uz.unidev.timetable.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import uz.unidev.timetable.BuildConfig
import uz.unidev.timetable.di.dataModule
import uz.unidev.timetable.di.repositoryModule
import uz.unidev.timetable.di.viewModelModule

/**
 *  Created by Nurlibay Koshkinbaev on 15/10/2022 23:01
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //Koin
        val modules = listOf(
            dataModule, repositoryModule, viewModelModule
        )
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            koin.loadModules(modules)
        }
    }
}