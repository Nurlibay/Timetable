package uz.unidev.timetable.data.source.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uz.unidev.timetable.data.models.StudentData
import uz.unidev.timetable.utils.Constants

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 23:31
 */

class ProfileHelper(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {
    fun getProfileData(
        onSuccess: (studentData: StudentData) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        db.collection(Constants.STUDENTS).document(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val result = it.toObject(StudentData::class.java)
                result?.let { student ->
                    onSuccess.invoke(student)
                } ?: onFailure.invoke("Student data is empty")
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
}