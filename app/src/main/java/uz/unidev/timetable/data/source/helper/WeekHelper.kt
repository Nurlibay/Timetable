package uz.unidev.timetable.data.source.helper

import com.google.firebase.firestore.FirebaseFirestore
import uz.unidev.timetable.data.models.WeekData
import uz.unidev.timetable.utils.Constants

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 19:05
 */

class WeekHelper(
    private val db: FirebaseFirestore
) {

    fun getWeekData(
        groupId: String,
        onSuccess: (groups: List<WeekData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        db.collection(Constants.TIMETABLE).document(groupId).collection(Constants.WEEKS).get()
            .addOnSuccessListener {
                val weeks = it.documents.map { doc ->
                    doc.toObject(WeekData::class.java)!!
                }
                onSuccess.invoke(weeks)
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
}