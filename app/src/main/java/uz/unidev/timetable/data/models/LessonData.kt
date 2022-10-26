package uz.unidev.timetable.data.models

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 15:55
 */

data class LessonData(
    var id: String = "",
    var name: String = "",
    var room: String = "",
    var startTime: String = "",
    var endTime: String = "",
    var teacher: String = ""
)