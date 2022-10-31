package uz.unidev.timetable.presentation.main.teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.data.models.TeacherData
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 18:48
 */

class TeacherViewModel(
    private val mainRepository: MainRepository
): ViewModel() {

    private var mutableTeacherData: MutableLiveData<Resource<List<TeacherData>>> = MutableLiveData()
    val teacherData: LiveData<Resource<List<TeacherData>>> get() = mutableTeacherData

    fun getTeachersData() {
        mutableTeacherData.value = Resource.loading()
        mainRepository.getTeachersData(
            {
                mutableTeacherData.value = Resource.success(it)
            },
            {
                mutableTeacherData.value = Resource.error(it)
            }
        )
    }

}