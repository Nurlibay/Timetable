package uz.unidev.timetable.presentation.main.timetable.editLesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 27/10/2022 15:10
 */

class EditLessonViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var mutableEditLesson: MutableLiveData<Resource<String>> = MutableLiveData()
    val editLesson: LiveData<Resource<String>> get() = mutableEditLesson

    fun editLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData
    ) {
        mutableEditLesson.value = Resource.loading()
        mainRepository.editLesson(
            groupId,
            weekName,
            lessonData,
            {
                mutableEditLesson.value = Resource.success(it)
            },
            {
                mutableEditLesson.value = Resource.error(it)
            }
        )
    }
}