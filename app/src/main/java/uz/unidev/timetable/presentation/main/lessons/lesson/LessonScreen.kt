package uz.unidev.timetable.presentation.main.lessons.lesson

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
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

    private var groupId: String = ""
    private var weekId: String = ""
    private var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgs()
        setupAdapter()
    }

    private fun initArgs() {
        groupId = requireArguments().getString("group_id") ?: ""
        weekId = requireArguments().getString("week_id") ?: ""
        pos = requireArguments().getInt("pos")
    }

    override fun onResume() {
        super.onResume()
//        binding.apply {
//            when (pos) {
//                0 -> viewModel.getLessonData(groupId, weekId, Constants.MONDAY)
//                1 -> viewModel.getLessonData(groupId, weekId, Constants.TUESDAY)
//                2 -> viewModel.getLessonData(groupId, weekId, Constants.WEDNESDAY)
//                3 -> viewModel.getLessonData(groupId, weekId, Constants.THURSDAY)
//                4 -> viewModel.getLessonData(groupId, weekId, Constants.FRIDAY)
//                5 -> viewModel.getLessonData(groupId, weekId, Constants.SATURDAY)
//                else -> viewModel.getLessonData(groupId, weekId, Constants.SUNDAY)
//            }
//            setupObserver()
//        }
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