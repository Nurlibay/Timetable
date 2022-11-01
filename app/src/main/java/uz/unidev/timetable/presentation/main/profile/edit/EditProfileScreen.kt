package uz.unidev.timetable.presentation.main.profile.edit

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenEditProfileBinding
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.showMessage

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 23:09
 */

class EditProfileScreen: Fragment(R.layout.screen_edit_profile) {

    private val binding: ScreenEditProfileBinding by viewBinding()
    private val viewModel: EditProfileViewModel by viewModel()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrentStudentData()
        setupObserver()
        binding.apply {
            btnCancel.setOnClickListener {
                navController.popBackStack()
            }
            containerImage.setOnClickListener {

            }
        }
    }

    private fun setupObserver() {
        viewModel.studentData.observe(viewLifecycleOwner) {
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