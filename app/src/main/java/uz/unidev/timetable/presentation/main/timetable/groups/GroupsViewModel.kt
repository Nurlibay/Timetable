package uz.unidev.timetable.presentation.main.timetable.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 18:37
 */

class GroupsViewModel(
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

    private var mutableSearch: MutableLiveData<Resource<List<GroupData>>> = MutableLiveData()
    val search: LiveData<Resource<List<GroupData>>> get() = mutableSearch

    fun getFilteredGroups(query: String) {
        mutableSearch.value = Resource.loading()
        mainRepository.getFilteredGroups(
            query,
            {
                mutableSearch.value = Resource.success(it)
            },
            {
                mutableSearch.value = Resource.error(it)
            }
        )
    }
}