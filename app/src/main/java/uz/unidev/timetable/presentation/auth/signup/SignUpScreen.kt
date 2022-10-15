package uz.unidev.timetable.presentation.auth.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenSignupBinding

/**
 *  Created by Nurlibay Koshkinbaev on 15/10/2022 23:06
 */

class SignUpScreen : Fragment(R.layout.screen_signup) {

    private val binding: ScreenSignupBinding by viewBinding()
    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignIn.setOnClickListener {
            navController.navigate(SignUpScreenDirections.actionSignUpScreenToSignInScreen())
        }
    }

}