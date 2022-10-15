package uz.unidev.timetable.presentation.auth.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenSigninBinding

/**
 *  Created by Nurlibay Koshkinbaev on 15/10/2022 23:06
 */

class SignInScreen : Fragment(R.layout.screen_signin) {

    private val binding: ScreenSigninBinding by viewBinding()
    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignUp.setOnClickListener {
            navController.navigate(SignInScreenDirections.actionSignInScreenToSignUpScreen())
        }
    }

}