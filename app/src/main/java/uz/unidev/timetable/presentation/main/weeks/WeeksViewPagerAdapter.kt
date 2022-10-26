package uz.unidev.timetable.presentation.main.weeks

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.unidev.timetable.presentation.main.weeks.week.WeekScreen

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:10
 */

class WeeksViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int) = when (position) {
        0 -> WeekScreen(position)
        2 -> WeekScreen(position)
        3 -> WeekScreen(position)
        else -> WeekScreen(position)
    }
}