package uz.unidev.timetable.app

import android.app.Application
import timber.log.Timber
import uz.unidev.timetable.BuildConfig

/**
 *  Created by Nurlibay Koshkinbaev on 15/10/2022 23:01
 */

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}