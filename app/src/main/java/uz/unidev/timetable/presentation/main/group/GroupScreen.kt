package uz.unidev.timetable.presentation.main.group

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.databinding.ScreenHomeBinding
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.addVerticalDivider
import uz.unidev.timetable.utils.extensions.showMessage

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 16:10
 */

class GroupScreen : Fragment(R.layout.screen_home) {

    private val binding: ScreenHomeBinding by viewBinding()
    private val viewModel: GroupViewModel by viewModel()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val adapter by lazy { GroupAdapter() }
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewModel.getGroupData()
        setupObserver()

        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().lowercase()
            adapter.query = text.toString()
            filteredGroupsByName(query)
        }
    }

    private fun filteredGroupsByName(query: String) {
        if (query.isNotEmpty()) {
            handler.postDelayed({
                viewModel.getFilteredGroups(query)
            }, 300)
            setupObserverFilteredGroups()
        } else {
            viewModel.getGroupData()
        }
    }

    private fun setupAdapter() {
        binding.apply {
            rvGroups.adapter = adapter
            rvGroups.addVerticalDivider(requireContext())
            rvGroups.setHasFixedSize(true)
        }
        adapter.setOnItemClickListener {
            navController.navigate(GroupScreenDirections.actionHomeScreenToWeekScreen(it))
        }
    }

    private fun setupObserver() {
        viewModel.group.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        it.data?.let { students ->
                            adapter.submitList(students)
                            binding.tvNoSearchResult.visibility = View.INVISIBLE
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

    private fun setupObserverFilteredGroups() {
        viewModel.search.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        it.data?.let { groupData: List<GroupData> ->
                            if (groupData.isNotEmpty()) {
                                binding.tvNoSearchResult.visibility = View.INVISIBLE
                                adapter.submitList(groupData)
                            } else {
                                binding.tvNoSearchResult.visibility = View.VISIBLE
                                adapter.submitList(emptyList())
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