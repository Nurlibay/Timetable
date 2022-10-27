package uz.unidev.timetable.presentation.main.lessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenTimetableBinding

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:03
 */

class TimetableScreen : Fragment(R.layout.screen_timetable) {

    private val binding: ScreenTimetableBinding by viewBinding()
    private val navController by lazy { findNavController() }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            pagerMain.adapter = DaysViewPagerAdapter(requireActivity())
            val list = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            TabLayoutMediator(binding.tabLayout, binding.pagerMain) { tab, pos ->
                tab.text = list[pos]
            }.attach()

            fab.setOnClickListener {
                navController.navigate(R.id.action_global_addLessonScreen)
            }
        }
    }
}