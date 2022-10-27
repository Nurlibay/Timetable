package uz.unidev.timetable.presentation.main.lessons

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.unidev.timetable.presentation.main.lessons.lesson.LessonScreen

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:10
 */

class DaysViewPagerAdapter(
    fa: FragmentActivity,
    private val groupId: String,
    private val weekId: String
) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 7

    override fun createFragment(position: Int): Fragment {
        val lessonScreen = LessonScreen()
        val args = Bundle()
        args.putInt("pos", position)
        args.putString("group_id", groupId)
        args.putString("week_id", weekId)
        lessonScreen.arguments = args
        return lessonScreen
    }
}