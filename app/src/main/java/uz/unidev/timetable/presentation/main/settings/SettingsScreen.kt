package uz.unidev.timetable.presentation.main.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.unidev.timetable.R
import uz.unidev.timetable.databinding.ScreenSettingsBinding
import uz.unidev.timetable.utils.extensions.onClick

/**
 *  Created by Nurlibay Koshkinbaev on 28/10/2022 15:52
 */

class SettingsScreen: Fragment(R.layout.screen_settings) {

    private val binding: ScreenSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvLang.text = resources.getString(R.string.lang_text)
            containerSelectLanguage.onClick {
                val dialog = LanguageDialog()
                dialog.show(requireActivity().supportFragmentManager, "LanguageDialog")
            }
            iconBack.onClick {
                findNavController().popBackStack()
            }
        }
    }
}