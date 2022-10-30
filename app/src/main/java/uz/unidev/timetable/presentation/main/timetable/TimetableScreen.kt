package uz.unidev.timetable.presentation.main.timetable

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenTimetableBinding
import uz.unidev.timetable.utils.ObservableData

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:03
 */

class TimetableScreen : Fragment(R.layout.screen_timetable) {

    private val binding: ScreenTimetableBinding by viewBinding()
    private val navController by lazy { findNavController() }
    private val args: TimetableScreenArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            pagerMain.adapter = DaysViewPagerAdapter(requireActivity(), args.groupId, args.weekData.id)
            val list = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            TabLayoutMediator(binding.tabLayout, binding.pagerMain) { tab, pos ->
                tab.text = list[pos]
            }.attach()

            iconBack.setOnClickListener {
                navController.navigateUp()
            }

            fab.setOnClickListener {
                navController.navigate(TimetableScreenDirections.actionGlobalAddLessonScreen(args.groupId, args.weekData.id))
            }

            tvStartDate.text = args.weekData.startDate + "-"
            tvEndDate.text = args.weekData.endDate
        }
    }

    override fun onResume() {
        super.onResume()
        ObservableData.isOnResume.value = true
    }
}