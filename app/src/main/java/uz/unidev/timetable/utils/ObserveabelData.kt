package uz.unidev.timetable.utils

import androidx.lifecycle.MutableLiveData

object ObservableData {

    var isOnResume = MutableLiveData(false)
    var isEditMenuClicked = MutableLiveData(false)

}