package uz.unidev.timetable.domain

import uz.unidev.timetable.data.models.Student
import uz.unidev.timetable.data.source.helper.AuthHelper
import uz.unidev.timetable.data.source.helper.ProfileHelper

class MainRepositoryImpl(
    private val authHelper: AuthHelper,
    private val profileHelper: ProfileHelper
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
        onSuccess: (student: Student) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        profileHelper.getProfileData(onSuccess, onFailure)
    }
}