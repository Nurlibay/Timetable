package uz.unidev.timetable.data.source.helper

import com.google.firebase.firestore.FirebaseFirestore
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.utils.Constants
import java.util.*
import kotlin.collections.HashMap

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
        db.collection(Constants.TIMETABLE).document(groupId).collection(Constants.WEEKS)
            .document(weekName).collection(dayName).get()
            .addOnSuccessListener {
                val lessonData = it.documents.map { doc ->
                    doc.toObject(LessonData::class.java)!!
                }
                onSuccess.invoke(lessonData)
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun addLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val lessonId = UUID.randomUUID().toString()

        db.collection(Constants.TIMETABLE).document(groupId).collection(Constants.WEEKS)
            .document(weekName).collection(lessonData.dayName).document(lessonId).set(
                LessonData(
                    lessonId,
                    lessonData.name,
                    lessonData.room,
                    lessonData.startTime,
                    lessonData.endTime,
                    lessonData.teacher,
                    lessonData.dayName,
                    lessonData.subGroup,
                    lessonData.lessonType,
                    lessonData.groups
                )
            )
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun editLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {

        val map = HashMap<String, Any>()
        map["id"] = lessonData.id
        map["name"] = lessonData.name
        map["room"] = lessonData.room
        map["startTime"] = lessonData.startTime
        map["endTime"] = lessonData.endTime
        map["teacher"] = lessonData.teacher
        map["dayName"] = lessonData.dayName
        map["subGroup"] = lessonData.subGroup
        map["lessonType"] = lessonData.lessonType

        db.collection(Constants.TIMETABLE).document(groupId).collection(Constants.WEEKS)
            .document(weekName).collection(lessonData.dayName).document(lessonData.id).update(map)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun deleteLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData,
        onSuccess: (msg: String) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        db.collection(Constants.TIMETABLE).document(groupId).collection(Constants.WEEKS)
            .document(weekName).collection(lessonData.dayName).document(lessonData.id).delete()
            .addOnSuccessListener {
                onSuccess.invoke("Lesson Successfully deleted")
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
}