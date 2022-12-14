package uz.unidev.timetable.data.source.pref

import android.content.Context
import android.content.SharedPreferences
import uz.unidev.timetable.utils.BooleanPreference
import uz.unidev.timetable.utils.Constants
import uz.unidev.timetable.utils.StringPreference

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 19:03
 */

class SharedPref(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)

    var isSigned: Boolean by BooleanPreference(pref, false)

    var language: String by StringPreference(pref, "ru")
}