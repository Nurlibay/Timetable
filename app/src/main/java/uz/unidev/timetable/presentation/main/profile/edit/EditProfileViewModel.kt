package uz.unidev.timetable.presentation.main.profile.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.StudentData
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 17/10/2022 13:52
 */

class EditProfileViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var mutableStudentData: MutableLiveData<Resource<StudentData>> = MutableLiveData()
    val studentData: LiveData<Resource<StudentData>> get() = mutableStudentData

    fun getCurrentStudentData() {
        mutableStudentData.value = Resource.loading()
        mainRepository.getProfileData(
            {
                mutableStudentData.value = Resource.success(it)
            },
            {
                mutableStudentData.value = Resource.error(it)
            }
        )
    }
}