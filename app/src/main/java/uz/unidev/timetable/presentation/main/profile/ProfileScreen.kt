package uz.unidev.timetable.presentation.main.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenProfileBinding
import uz.unidev.timetable.presentation.main.MainContainer
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.showMessage

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 18:47
 */

class ProfileScreen : Fragment(R.layout.screen_profile) {

    private val binding: ScreenProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var parentNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProfileData()
        setupObserver()
        binding.apply {
            imageEdit.setOnClickListener {
                parentNavController = (parentFragment?.parentFragment as MainContainer).findNavController()
                parentNavController.navigate(R.id.action_mainContainer_to_editProfileScreen)
            }
            iconSettings.setOnClickListener {
                parentNavController = (parentFragment?.parentFragment as MainContainer).findNavController()
                parentNavController.navigate(R.id.action_mainContainer_to_settingsScreen)
            }
        }
    }

    private fun setupObserver() {
        viewModel.profile.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        it.data?.let { student ->
                            etFullName.setText(student.fullName)
                            etEmail.setText(student.email)
                            etPhoneNumber.setText(student.phoneNumber)
                        }
                    }
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


    private fun setLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.isVisible = isLoading
        }
    }
}