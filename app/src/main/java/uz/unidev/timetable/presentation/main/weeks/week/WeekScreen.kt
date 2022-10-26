package uz.unidev.timetable.presentation.main.weeks.week

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenWeekBinding

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 17:32
 */

class WeekScreen(private val type: Int) : Fragment(R.layout.screen_week) {

    private val binding: ScreenWeekBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            when (type) {
                0 -> {
                    text.text = "Week 1"
                }
                1 -> {
                    text.text = "Week 2"
                }
                2 -> {
                    text.text = "Week 3"
                }
                else -> {
                    text.text = "Week 4"
                }
            }
        }
    }
}