package uz.unidev.timetable.presentation.main.timetable.lesson

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.NavAuthDirections
import uz.unidev.timetable.NavMainDirections
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenLessonBinding
import uz.unidev.timetable.presentation.main.MainContainer
import uz.unidev.timetable.presentation.main.timetable.TimetableScreenDirections
import uz.unidev.timetable.presentation.main.timetable.editLesson.EditLessonScreen
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
    private val navController by lazy { findNavController() }
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    private var groupId: String = ""
    private var weekId: String = ""
    private var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgs()
        setupAdapter()
        binding.swipeRefresh.setOnRefreshListener {
            getLessonData()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun initArgs() {
        groupId = requireArguments().getString("group_id") ?: ""
        weekId = requireArguments().getString("week_id") ?: ""
        pos = requireArguments().getInt("pos")
    }

    override fun onResume() {
        super.onResume()
        getLessonData()
        setupObserver()
        setupObserverDeleteLesson()
    }

    private fun getLessonData() {
        when (pos) {
            0 -> viewModel.getLessonData(groupId, weekId, Constants.MONDAY)
            1 -> viewModel.getLessonData(groupId, weekId, Constants.TUESDAY)
            2 -> viewModel.getLessonData(groupId, weekId, Constants.WEDNESDAY)
            3 -> viewModel.getLessonData(groupId, weekId, Constants.THURSDAY)
            4 -> viewModel.getLessonData(groupId, weekId, Constants.FRIDAY)
            5 -> viewModel.getLessonData(groupId, weekId, Constants.SATURDAY)
            else -> viewModel.getLessonData(groupId, weekId, Constants.SUNDAY)
        }
    }

    private fun setupAdapter() {
        binding.apply {
            rvLessons.adapter = adapter
            rvLessons.addVerticalDivider(requireContext())
        }

        adapter.setOnItemLongClickListener { lessonData, item ->
            val popupMenu = PopupMenu(requireContext(), item)
            popupMenu.menuInflater.inflate(R.menu.menu_lesson, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit -> {
                        findNavController().navigate(TimetableScreenDirections.actionTimetableScreenToEditLessonScreen(groupId, weekId, lessonData))
                    }
                    R.id.delete -> {
                        viewModel.deleteLesson(groupId, weekId, lessonData)
                        getLessonData()
                    }
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
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
                            if (students.isEmpty()) {
                                binding.tvNoLesson.visibility = View.VISIBLE
                                adapter.submitList(emptyList())
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

    private fun setupObserverDeleteLesson() {
        viewModel.deleteLesson.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        it.data?.let { successMessage ->
                            showMessage(successMessage)
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