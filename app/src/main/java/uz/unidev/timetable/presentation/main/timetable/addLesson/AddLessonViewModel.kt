package uz.unidev.timetable.presentation.main.timetable.addLesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 27/10/2022 15:10
 */

class AddLessonViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var mutableAddLesson: MutableLiveData<Resource<String>> = MutableLiveData()
    val addLesson: LiveData<Resource<String>> get() = mutableAddLesson

    fun addLesson(
        groupId: String,
        weekName: String,
        lessonData: LessonData
    ) {
        mutableAddLesson.value = Resource.loading()
        mainRepository.addLesson(
            groupId,
            weekName,
            lessonData,
            {
                mutableAddLesson.value = Resource.success("")
            },
            {
                mutableAddLesson.value = Resource.error(it)
            }
        )
    }
}