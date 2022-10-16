package uz.unidev.timetable.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.unidev.timetable.data.source.helper.AuthHelper
import uz.unidev.timetable.data.source.helper.ProfileHelper
import uz.unidev.timetable.data.source.pref.SharedPref
import uz.unidev.timetable.domain.MainRepository
import uz.unidev.timetable.domain.MainRepositoryImpl
import uz.unidev.timetable.presentation.auth.signin.SignInViewModel
import uz.unidev.timetable.presentation.auth.signup.SignUpViewModel
import uz.unidev.timetable.presentation.main.profile.ProfileViewModel

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { AuthHelper(get(), get()) }
    single { ProfileHelper(get(), get()) }
}

val sharedPrefModule = module {
    single { SharedPref(androidApplication().applicationContext) }
}

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}