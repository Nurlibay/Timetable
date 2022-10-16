package uz.unidev.timetable.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject
import uz.unidev.timetable.R
import uz.unidev.timetable.data.source.pref.SharedPref
import uz.unidev.timetable.databinding.ContainerMainBinding

/**
 *  Created by Nurlibay Koshkinbaev on 16/10/2022 16:08
 */

class MainContainer : Fragment(R.layout.container_main) {

    private val sharedPref: SharedPref by inject()
    private val binding: ContainerMainBinding by viewBinding()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref.isSigned = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
        binding.bottomNavMenu.setupWithNavController(navController)
    }
}