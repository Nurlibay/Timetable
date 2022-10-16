package uz.unidev.timetable.domain

import uz.unidev.timetable.data.models.Student

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
        onSuccess: (student: Student) -> Unit,
        onFailure: (msg: String?) -> Unit
    )
}