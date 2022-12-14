package uz.unidev.timetable.domain

import uz.unidev.timetable.data.models.*

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 15:31
 */

interface MainRepository {
    fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun signUp(
        fullName: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun addStudentToDb(
        fullName: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun getProfileData(
        onSuccess: (studentData: StudentData) -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun getGroupData(
        onSuccess: (students: List<GroupData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun getTeachersData(
        onSuccess: (students: List<TeacherData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun getFilteredGroups(
        query: String?,
        onSuccess: (students: List<GroupData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun getWeekData(
        groupId: String,
        onSuccess: (students: List<WeekData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun getLessonData(
        groupId: String,
        weekName: String,
        dayName: String,
        onSuccess: (groups: List<LessonData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun addLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun deleteLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: (msg: String) -> Unit,
        onFailure: (msg: String?) -> Unit
    )

    fun editLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    )
}