package uz.unidev.timetable.presentation.main.timetable.addLesson

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.data.source.pref.SharedPref
import uz.unidev.timetable.databinding.ScreenAddLessonBinding
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.showMessage
import uz.unidev.timetable.utils.extensions.toDayEnglish
import java.util.*

/**
 *  Created by Nurlibay Koshkinbaev on 27/10/2022 14:46
 */

class AddLessonScreen : Fragment(R.layout.screen_add_lesson) {

    private val binding: ScreenAddLessonBinding by viewBinding()
    private val navController by lazy { findNavController() }
    private val viewModel: AddLessonViewModel by viewModel()
    private val args: AddLessonScreenArgs by navArgs()
    private val lessonType = MutableLiveData<List<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lessonType.postValue(listOf("Lecture", "Laboratory"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        binding.apply {
            val days = resources.getStringArray(R.array.days)
            val adapter = ArrayAdapter(requireContext(), R.layout.item_days, days)
            autoCompleteTextView.setAdapter(adapter)

            val subGroups = resources.getStringArray(R.array.subGroups)
            val subGroupAdapter = ArrayAdapter(requireContext(), R.layout.item_days, subGroups)
            autoCompleteTextViewSubGroup.setAdapter(subGroupAdapter)

            val lessonTypes = resources.getStringArray(R.array.lessonType)
            val lessonTypeAdapter = ArrayAdapter(requireContext(), R.layout.item_days, lessonTypes)
            autoCompleteTextViewLessonType.setAdapter(lessonTypeAdapter)

            autoCompleteTextViewLessonType.setOnItemClickListener { _, _, pos, _ ->
                if (autoCompleteTextViewLessonType.text.toString() == "Laboratory") {
                    tilSubGroup.visibility = View.VISIBLE
                } else {
                    tilSubGroup.visibility = View.GONE
                }
            }

            iconSetStartTime.setOnClickListener {
                openTimePickerForStartTime()
            }

            iconSetEndTime.setOnClickListener {
                openTimePickerForEndTime()
            }

            iconBack.setOnClickListener {
                navController.popBackStack()
            }

            iconDone.setOnClickListener {
                if (validate()) {
                    viewModel.addLesson(
                        args.groupId,
                        args.weekId,
                        LessonData(
                            UUID.randomUUID().toString(),
                            etName.text.toString(),
                            etRoom.text.toString(),
                            etStartTime.text.toString(),
                            etEndTime.text.toString(),
                            etTeacher.text.toString(),
                            autoCompleteTextView.text.toString().toDayEnglish().lowercase(),
                            autoCompleteTextViewSubGroup.text.toString(),
                            autoCompleteTextViewLessonType.text.toString()
                        )
                    )
                    navController.popBackStack()
                }
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (etName.text.toString().isEmpty()) {
                tilName.error = getString(R.string.empty_name)
            }
            if (etTeacher.text.toString().isEmpty()) {
                tilTeacher.error = getString(R.string.empty_teacher)
            }
            if (etRoom.text.toString().isEmpty()) {
                tilRoom.error = getString(R.string.empty_room)
            }
            if (etStartTime.text.toString().isEmpty()) {
                tilStartTime.error = getString(R.string.empty_start_time)
            }
            if (etEndTime.text.toString().isEmpty()) {
                tilEndTime.error = getString(R.string.empty_end_time)
            }
            if (autoCompleteTextView.text.toString().isEmpty()) {
                tilDay.error = getString(R.string.empty_day_name)
            } else if (etName.text.toString().isNotEmpty() && etTeacher.text.toString().isNotEmpty()
                && etRoom.text.toString().isNotEmpty() && etStartTime.text.toString().isNotEmpty()
                && etEndTime.text.toString().isNotEmpty() && autoCompleteTextView.text.toString()
                    .isNotEmpty()
            ) {
                return true
            }
        }
        return false
    }


    private fun setupObserver() {
        viewModel.addLesson.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        it.data?.let { successMessage ->
                            showMessage(successMessage)
                        }
                    }
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    showMessage(it.message.toString())
                }
                ResourceState.NETWORK_ERROR -> {
                    setLoading(false)
                    showMessage(getString(R.string.no_internet))
                }
            }
        }
    }

    private fun openTimePickerForStartTime() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText(getString(R.string.select_start_time))
            .build()
        picker.show(childFragmentManager, "TAG")
        picker.addOnPositiveButtonClickListener {
            binding.etStartTime.setText(String.format("%02d:%02d", picker.hour, picker.minute))
        }
    }

    private fun openTimePickerForEndTime() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText(getString(R.string.select_start_time))
            .build()
        picker.show(childFragmentManager, "TAG")
        picker.addOnPositiveButtonClickListener {
            binding.etEndTime.setText(String.format("%02d:%02d", picker.hour, picker.minute))
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.isVisible = isLoading
        }
    }
}