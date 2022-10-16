package uz.unidev.timetable.data.source.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uz.unidev.timetable.data.models.Student
import uz.unidev.timetable.utils.Constants

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 15:14
 */

class AuthHelper(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {

    fun signUp(
        fullName: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun addStudentToDb(fullName: String, onSuccess: () -> Unit, onFailure: (msg: String?) -> Unit) {
        val student = Student(auth.currentUser!!.uid, auth.currentUser!!.email!!, fullName, "", "")
        db.collection(Constants.STUDENTS).document(student.id).set(student)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

}