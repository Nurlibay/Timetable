package uz.unidev.timetable.presentation.main.timetable.weeks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.data.models.WeekData
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 18:37
 */

class WeekViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    private var mutableWeek: MutableLiveData<Resource<List<WeekData>>> = MutableLiveData()
    val week: LiveData<Resource<List<WeekData>>> get() = mutableWeek

    fun getGroupData(groupId: String) {
        mutableWeek.value = Resource.loading()
        mainRepository.getWeekData(
            groupId,
            {
                mutableWeek.value = Resource.success(it)
            },
            {
                mutableWeek.value = Resource.error(it)
            }
        )
    }
}