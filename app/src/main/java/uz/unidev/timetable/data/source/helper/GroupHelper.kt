package uz.unidev.timetable.data.source.helper

import com.google.firebase.firestore.FirebaseFirestore
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.utils.Constants

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 14:21
 */

class GroupHelper(
    private val db: FirebaseFirestore
) {

    fun getGroupData(
        onSuccess: (groups: List<GroupData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        db.collection(Constants.GROUPS).get()
            .addOnSuccessListener {
                val groups = it.documents.map { doc ->
                    doc.toObject(GroupData::class.java)!!
                }
                onSuccess.invoke(groups)
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun getFilteredGroups(
        query: String?,
        onSuccess: (groups: List<GroupData>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        db.collection(Constants.GROUPS).get()
            .addOnSuccessListener {
                val groups = it.documents.map { doc ->
                    doc.toObject(GroupData::class.java)!!
                }
                val filteredGroups = arrayListOf<GroupData>()
                groups.forEach { groupData ->
                    if (groupData.name.contains(query!!)) {
                        filteredGroups.add(groupData)
                    }
                }
                onSuccess.invoke(filteredGroups)
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
}