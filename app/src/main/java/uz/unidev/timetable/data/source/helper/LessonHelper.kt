package uz.unidev.timetable.data.source.helper

import com.google.firebase.firestore.FirebaseFirestore
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.data.models.WeekData
import uz.unidev.timetable.utils.Constants

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:01
 */

class LessonHelper(
    private val db: FirebaseFirestore
) {

    fun getLessonData(
        groupId: String,
        weekName: String,
        dayName: String,
        onSuccess: (groups: List<LessonData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        db.collection(Constants.TIMETABLE).document(groupId).collection(Constants.WEEKS).document(weekName).collection(dayName).get()
            .addOnSuccessListener {
                val days = it.documents.map { doc ->
                    doc.toObject(LessonData::class.java)!!
                }
                onSuccess.invoke(days)
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

}