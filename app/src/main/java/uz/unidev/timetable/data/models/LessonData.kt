package uz.unidev.timetable.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 15:55
 */

@Parcelize
data class LessonData(
    var id: String = "",
    var name: String = "",
    var room: String = "",
    var startTime: String = "",
    var endTime: String = "",
    var teacher: String = "",
    var dayName: String = "",
    var subGroup: String = "",
    var lessonType: Int = -1,
    var groups: List<String> = emptyList()
): Parcelable