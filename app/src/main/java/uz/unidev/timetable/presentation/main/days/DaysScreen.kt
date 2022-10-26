package uz.unidev.timetable.presentation.main.days

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenDaysBinding

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:03
 */

class DaysScreen : Fragment(R.layout.screen_days) {

    private val binding: ScreenDaysBinding by viewBinding()
    private val args: DaysScreenArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            pagerMain.adapter = DaysViewPagerAdapter(requireActivity())
            val list = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            TabLayoutMediator(binding.tabLayout, binding.pagerMain) { tab, pos ->
                tab.text = list[pos]
            }.attach()

            iconBack.setOnClickListener {
                findNavController().navigateUp()
            }
            tvStartDate.text = args.weekData.startDate + " - "
            tvEndDate.text = args.weekData.endDate
        }
    }
}