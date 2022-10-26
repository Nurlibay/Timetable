package uz.unidev.timetable.presentation.main.weeks

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.unidev.timetable.presentation.main.weeks.week1.Week1Screen
import uz.unidev.timetable.presentation.main.weeks.week2.Week2Screen
import uz.unidev.timetable.presentation.main.weeks.week3.Week3Screen
import uz.unidev.timetable.presentation.main.weeks.week4.Week4Screen

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:10
 */

class WeeksViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int) = when (position) {
        0 -> Week1Screen()
        2 -> Week2Screen()
        3 -> Week3Screen()
        else -> Week4Screen()
    }
}