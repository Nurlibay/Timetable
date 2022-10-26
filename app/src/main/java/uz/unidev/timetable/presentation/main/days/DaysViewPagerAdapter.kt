package uz.unidev.timetable.presentation.main.days

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.unidev.timetable.presentation.main.days.day.DayScreen

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:10
 */

class DaysViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 7

    override fun createFragment(position: Int) = when (position) {
        0 -> DayScreen(position)
        2 -> DayScreen(position)
        3 -> DayScreen(position)
        4 -> DayScreen(position)
        5 -> DayScreen(position)
        6 -> DayScreen(position)
        else -> DayScreen(position)
    }
}