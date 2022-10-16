package uz.unidev.timetable.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.Student
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 18:48
 */

class ProfileViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    private var mutableProfile: MutableLiveData<Resource<Student>> = MutableLiveData()
    val profile: LiveData<Resource<Student>> get() = mutableProfile

    fun getProfileData() {
        mutableProfile.value = Resource.loading()
        mainRepository.getProfileData(
            {
                mutableProfile.value = Resource.success(it)
            },
            {
                mutableProfile.value = Resource.error(it)
            }
        )
    }
}