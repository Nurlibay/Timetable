package uz.unidev.timetable.presentation.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject
import uz.unidev.timetable.MainActivity
import uz.unidev.timetable.R
import uz.unidev.timetable.data.source.pref.SharedPref
import uz.unidev.timetable.databinding.DialogChooseLanguageBinding

/**
 *  Created by Nurlibay Koshkinbaev on 28/10/2022 16:18
 */

class LanguageDialog : DialogFragment() {

    private val binding: DialogChooseLanguageBinding by viewBinding()
    private val pref: SharedPref by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_choose_language, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            containerEnglishLang.setOnClickListener {
                pref.language = "en"
                dismiss()
                (requireActivity() as MainActivity).setNewLocale()
                dismiss()
            }
            containerRusLang.setOnClickListener {
                pref.language = "ru"
                dismiss()
                (requireActivity() as MainActivity).setNewLocale()
                dismiss()
            }
        }
    }
}