package uz.unidev.timetable.data.source.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uz.unidev.timetable.data.models.Student
import uz.unidev.timetable.utils.Constants

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 23:31
 */

class ProfileHelper(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {
    fun getProfileData(
        onSuccess: (student: Student) -> Unit,
        onFailure: (msg: String?) -> Unit
    ){
        db.collection(Constants.STUDENTS).document()
    }
}