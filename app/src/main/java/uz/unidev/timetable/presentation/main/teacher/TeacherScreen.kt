package uz.unidev.timetable.presentation.main.teacher

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenTeacherBinding
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.addVerticalDivider
import uz.unidev.timetable.utils.extensions.showMessage

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 18:46
 */

class TeacherScreen : Fragment(R.layout.screen_teacher) {

    private val binding: ScreenTeacherBinding by viewBinding()
    private val viewModel: TeacherViewModel by viewModel()
    private val adapter by lazy { TeachersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewModel.getTeachersData()
        setupObserver()
    }

    private fun setupAdapter() {
        binding.apply {
            rvTeachers.adapter = adapter
            rvTeachers.addVerticalDivider(requireContext())
            rvTeachers.setHasFixedSize(true)
        }
    }

    private fun setupObserver() {
        viewModel.teacherData.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        it.data?.let { teachers ->
                            adapter.submitList(teachers)
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