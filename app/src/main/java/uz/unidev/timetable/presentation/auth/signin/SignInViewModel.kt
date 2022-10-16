package uz.unidev.timetable.presentation.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.utils.Resource

/**
 *  Created by Nurlibay Koshkinbaev on 15/10/2022 23:07
 */

class SignInViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {
    private var _signIn: MutableLiveData<Resource<Any?>> = MutableLiveData()
    val signInStatus: LiveData<Resource<Any?>> get() = _signIn

    fun signIn(email: String, password: String) {
        _signIn.value = Resource.loading()
        mainRepository.signIn(email, password,
            {
                _signIn.value = Resource.success(null)
            }, {
                _signIn.value = Resource.error(it)
            }
        )
    }
}