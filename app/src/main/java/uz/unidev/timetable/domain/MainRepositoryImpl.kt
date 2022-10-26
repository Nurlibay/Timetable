package uz.unidev.timetable.domain

import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.data.models.StudentData
import uz.unidev.timetable.data.models.WeekData
import uz.unidev.timetable.data.source.helper.*

class MainRepositoryImpl(
    private val authHelper: AuthHelper,
    private val profileHelper: ProfileHelper,
    private val groupHelper: GroupHelper,
    private val weekHelper: WeekHelper,
    private val lessonHelper: LessonHelper
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
}