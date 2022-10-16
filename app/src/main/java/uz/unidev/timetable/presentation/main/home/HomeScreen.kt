package uz.unidev.timetable.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenHomeBinding

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 16:10
 */

class HomeScreen: Fragment(R.layout.screen_home) {

    private val binding: ScreenHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModel()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}