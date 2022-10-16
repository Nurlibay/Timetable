package uz.unidev.timetable.presentation.auth.signup

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenSignupBinding
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.showMessage

/**
 *  Created by Nurlibay Koshkinbaev on 15/10/2022 23:06
 */

class SignUpScreen : Fragment(R.layout.screen_signup) {

    private val binding: ScreenSignupBinding by viewBinding()
    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val viewModel: SignUpViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvSignIn.setOnClickListener {
                navController.navigate(SignUpScreenDirections.actionSignUpScreenToSignInScreen())
            }
            btnSignUp.setOnClickListener {
                if (validate()) {
                    viewModel.signUp(
                        etFullName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                }
            }
        }
        setupObserverSignUpStatus()
    }

    private fun setupObserverSignUpStatus() {
        viewModel.signUpStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    navController.navigate(SignUpScreenDirections.actionSignUpScreenToSignInScreen())
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    showMessage(it.message.toString())
                }
                ResourceState.NETWORK_ERROR -> {
                    setLoading(false)
                    showMessage(getString(R.string.no_internet))
                }
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            return if (etEmail.text!!.isNotEmpty() && etPassword.text!!.isNotEmpty()
                && etFullName.text!!.isNotEmpty() && etPassword.length() >= 6
            ) {
                true
            } else if (etPassword.length() < 6) {
                tilPassword.error = getString(R.string.password_length_condition)
                false
            } else if (etFullName.text.isNullOrEmpty()) {
                tilPassword.error = getString(R.string.enter_full_name)
                false
            } else {
                false
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.isVisible = isLoading
            etEmail.isEnabled = !isLoading
            etPassword.isEnabled = !isLoading
        }
    }
}