package uz.unidev.timetable.presentation.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 15/10/2022 23:07
 */

class SignUpViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    private var _signUp: MutableLiveData<Resource<Any?>> = MutableLiveData()
    val signUpStatus: LiveData<Resource<Any?>> get() = _signUp

    fun signUp(fullName: String, email: String, password: String) {
        _signUp.value = Resource.loading()
        mainRepository.signUp(fullName, email, password,
            {
                _signUp.value = Resource.success(null)
            }, {
                _signUp.value = Resource.error(it)
            }
        )
    }
}