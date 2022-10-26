package uz.unidev.timetable.presentation.main.days.day

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenDayBinding

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 17:32
 */

class DayScreen(private val type: Int) : Fragment(R.layout.screen_day) {

    private val binding: ScreenDayBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            when (type) {
                0 -> {
                    tvDayName.text = "Monday"
                }
                1 -> {
                    tvDayName.text = "Tuesday"
                }
                2 -> {
                    tvDayName.text = "Wednesday"
                }
                3 -> {
                    tvDayName.text = "Thursday"
                }
                4 -> {
                    tvDayName.text = "Friday"
                }
                5 -> {
                    tvDayName.text = "Saturday"
                }
                else -> {
                    tvDayName.text = "Sunday"
                }
            }
        }
    }
}