package uz.unidev.timetable.domain

import uz.unidev.timetable.data.models.*
import uz.unidev.timetable.data.source.helper.*

class MainRepositoryImpl(
    private val authHelper: AuthHelper,
    private val profileHelper: ProfileHelper,
    private val groupHelper: GroupHelper,
    private val weekHelper: WeekHelper,
    private val lessonHelper: LessonHelper,
    private val teacherHelper: TeacherHelper
) : MainRepository {
    override fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        authHelper.signIn(email, password, onSuccess, onFailure)
    }

    override fun signUp(
        fullName: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        authHelper.signUp(fullName, email, password, onSuccess, onFailure)
    }

    override fun addStudentToDb(
        fullName: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        authHelper.addStudentToDb(fullName, onSuccess, onFailure)
    }

    override fun getProfileData(
        onSuccess: (studentData: StudentData) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        profileHelper.getProfileData(onSuccess, onFailure)
    }

    override fun getGroupData(
        onSuccess: (students: List<GroupData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        groupHelper.getGroupData(onSuccess, onFailure)
    }

    override fun getTeachersData(
        onSuccess: (students: List<TeacherData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        teacherHelper.getTeachersData(onSuccess, onFailure)
    }

    override fun getFilteredGroups(
        query: String?,
        onSuccess: (students: List<GroupData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        groupHelper.getFilteredGroups(query, onSuccess, onFailure)
    }

    override fun getWeekData(
        groupId: String,
        onSuccess: (students: List<WeekData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        weekHelper.getWeekData(groupId, onSuccess, onFailure)
    }

    override fun getLessonData(
        groupId: String,
        weekName: String,
        dayName: String,
        onSuccess: (groups: List<LessonData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        lessonHelper.getLessonData(groupId, weekName, dayName, onSuccess, onFailure)
    }

    override fun addLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: (msg: String) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        lessonHelper.addLesson(groupId, weekName, lessonData, onSuccess, onFailure)
    }

    override fun deleteLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: (msg: String) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        lessonHelper.deleteLesson(groupId, weekName, lessonData, onSuccess, onFailure)
    }

    override fun editLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: (msg: String) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        lessonHelper.editLesson(groupId, weekName, lessonData, onSuccess, onFailure)
    }
}