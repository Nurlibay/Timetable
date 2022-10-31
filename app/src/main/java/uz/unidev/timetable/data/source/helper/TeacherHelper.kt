package uz.unidev.timetable.data.source.helper

import com.google.firebase.firestore.FirebaseFirestore
import uz.unidev.timetable.data.models.TeacherData
import uz.unidev.timetable.utils.Constants

/**
 *  Created by Nurlibay Koshkinbaev on 31/10/2022 20:17
 */

class TeacherHelper(
    private val db: FirebaseFirestore
) {
    fun getTeachersData(
        onSuccess: (teachers: List<TeacherData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ){
        db.collection(Constants.TEACHERS).get()
            .addOnSuccessListener {
                val teachers = it.documents.map { documents ->
                    documents.toObject(TeacherData::class.java)!!
                }
                onSuccess.invoke(teachers)
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
}