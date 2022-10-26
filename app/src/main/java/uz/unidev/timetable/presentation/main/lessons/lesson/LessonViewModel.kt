package uz.unidev.timetable.presentation.main.lessons.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 18:37
 */

class LessonViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var mutableLesson: MutableLiveData<Resource<List<LessonData>>> = MutableLiveData()
    val lesson: LiveData<Resource<List<LessonData>>> get() = mutableLesson

    fun getLessonData(
        groupId: String,
        weekName: String,
        dayName: String,
    ) {
        mutableLesson.value = Resource.loading()
        mainRepository.getLessonData(
            groupId,
            weekName,
            dayName,
            {
                mutableLesson.value = Resource.success(it)
            },
            {
                mutableLesson.value = Resource.error(it)
            }
        )
    }
}