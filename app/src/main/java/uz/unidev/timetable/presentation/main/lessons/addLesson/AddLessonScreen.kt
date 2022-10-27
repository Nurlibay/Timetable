package uz.unidev.timetable.presentation.main.lessons.addLesson

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenAddLessonBinding

/**
 *  Created by Nurlibay Koshkinbaev on 27/10/2022 14:46
 */

class AddLessonScreen: Fragment(R.layout.screen_add_lesson) {

    private val binding: ScreenAddLessonBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val days = resources.getStringArray(R.array.days)
            val adapter = ArrayAdapter(requireContext(), R.layout.item_days, days)
            autoCompleteTextView.setAdapter(adapter)
        }
    }

}