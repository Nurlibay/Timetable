package uz.unidev.timetable.presentation.main.weeks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenWeeksBinding

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 16:03
 */

class WeeksScreen : Fragment(R.layout.screen_weeks) {

    private val binding: ScreenWeeksBinding by viewBinding()
    private val navArgs: WeeksScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            pagerMain.adapter = WeeksViewPagerAdapter(requireActivity())
            val list = listOf("Week 1", "Week 2", "Week 3", "Week 4")
            TabLayoutMediator(binding.tabLayout, binding.pagerMain) { tab, pos ->
                tab.text = list[pos]
            }.attach()

            tvGroupNumber.text = navArgs.groupData.name
            iconBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}