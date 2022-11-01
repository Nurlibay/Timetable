package uz.unidev.timetable.presentation.main.timetable.weeks

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.databinding.ScreenWeekBinding
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.addVerticalDivider
import uz.unidev.timetable.utils.extensions.showMessage

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 16:10
 */

class WeekScreen: Fragment(R.layout.screen_week) {

    private val binding: ScreenWeekBinding by viewBinding()
    private val viewModel: WeekViewModel by viewModel()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val adapter by lazy { WeekAdapter() }
    private lateinit var groupData: GroupData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        groupData = requireArguments().getParcelable("group_data")!!
        viewModel.getGroupData(groupData.id)
        setupObserver()
        binding.iconBack.setOnClickListener {
            navController.navigateUp()
        }
        binding.tvGroupNumber.text = groupData.name
    }

    private fun setupAdapter() {
        binding.apply {
            rvWeeks.adapter = adapter
            rvWeeks.addVerticalDivider(requireContext())
        }
        adapter.setOnItemClickListener {
            navController.navigate(WeekScreenDirections.actionWeekScreenToTimetableScreen(it, groupData.id))
        }
    }

    private fun setupObserver() {
        viewModel.week.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        it.data?.let { weeks ->
                            adapter.submitList(weeks)
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