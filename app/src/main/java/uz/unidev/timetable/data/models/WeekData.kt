package uz.unidev.timetable.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 19:04
 */

@Parcelize
data class WeekData(
    var name: String = "",
    var startDate: String = "",
    var endDate: String = "",
) : Parcelable