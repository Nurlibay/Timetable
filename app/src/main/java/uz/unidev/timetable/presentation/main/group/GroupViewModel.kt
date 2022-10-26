package uz.unidev.timetable.presentation.main.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 18:37
 */

class GroupViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    private var mutableGroup: MutableLiveData<Resource<List<GroupData>>> = MutableLiveData()
    val group: LiveData<Resource<List<GroupData>>> get() = mutableGroup

    fun getGroupData() {
        mutableGroup.value = Resource.loading()
        mainRepository.getGroupData(
            {
                mutableGroup.value = Resource.success(it)
            },
            {
                mutableGroup.value = Resource.error(it)
            }
        )
    }
}