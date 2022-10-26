package uz.unidev.timetable.presentation.main.lessons.lesson

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenLessonBinding
import uz.unidev.timetable.utils.Constants
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.addVerticalDivider
import uz.unidev.timetable.utils.extensions.showMessage

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 17:32
 */

class LessonScreen : Fragment(R.layout.screen_lesson) {

    private val binding: ScreenLessonBinding by viewBinding()
    private val viewModel: LessonViewModel by viewModel()
    private val adapter by lazy { LessonAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        Timber.tag("gruppa").d(requireArguments().getString("group_id", ""))
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            when (requireArguments().getInt("pos")) {
                0 -> viewModel.getLessonData(requireArguments().getString("group_id")!!, requireArguments().getString("week_id")!!, Constants.MONDAY)
                1 -> viewModel.getLessonData(requireArguments().getString("group_id")!!, requireArguments().getString("week_id")!!, Constants.TUESDAY)
                2 -> viewModel.getLessonData(requireArguments().getString("group_id")!!, requireArguments().getString("week_id")!!, Constants.WEDNESDAY)
                3 -> viewModel.getLessonData(requireArguments().getString("group_id")!!, requireArguments().getString("week_id")!!, Constants.THURSDAY)
                4 -> viewModel.getLessonData(requireArguments().getString("group_id")!!, requireArguments().getString("week_id")!!, Constants.FRIDAY)
                5 -> viewModel.getLessonData(requireArguments().getString("group_id")!!, requireArguments().getString("week_id")!!, Constants.SATURDAY)
                else -> viewModel.getLessonData(requireArguments().getString("group_id")!!, requireArguments().getString("week_id")!!, Constants.SUNDAY)
            }
            setupObserver()
        }
    }

    private fun setupAdapter() {
        binding.apply {
            rvLessons.adapter = adapter
            rvLessons.addVerticalDivider(requireContext())
        }
    }

    private fun setupObserver() {
        viewModel.lesson.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        it.data?.let { students ->
                            if(students.isNullOrEmpty()){
                                binding.tvNoLesson.visibility = View.VISIBLE
                            } else {
                                adapter.submitList(students)
                                binding.tvNoLesson.visibility = View.INVISIBLE
                            }
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