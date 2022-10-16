package uz.unidev.timetable.domain

import uz.unidev.timetable.data.source.helper.AuthHelper

class MainRepositoryImpl(
    private val authHelper: AuthHelper
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
}