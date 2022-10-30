package uz.unidev.timetable.presentation.main.timetable.editLesson

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.databinding.ScreenAddLessonBinding
import uz.unidev.timetable.databinding.ScreenEditLessonBinding
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.showMessage
import java.util.UUID

/**
 *  Created by Nurlibay Koshkinbaev on 27/10/2022 14:46
 */

class EditLessonScreen : Fragment(R.layout.screen_edit_lesson) {

    private val binding: ScreenEditLessonBinding by viewBinding()
    private val navController by lazy { findNavController() }
    private val viewModel: EditLessonViewModel by viewModel()
    private val args by navArgs<EditLessonScreenArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setData()
        binding.apply {
            val days = resources.getStringArray(R.array.days)
            val adapter = ArrayAdapter(requireContext(), R.layout.item_days, days)
            autoCompleteTextView.setAdapter(adapter)

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
                if(validate()) {
                    viewModel.editLesson(
                        args.groupId,
                        args.weekId,
                        LessonData(
                            args.lessonData.id,
                            etName.text.toString(),
                            etRoom.text.toString(),
                            etStartTime.text.toString(),
                            etEndTime.text.toString(),
                            etTeacher.text.toString(),
                            autoCompleteTextView.text.toString().lowercase()
                        )
                    )
                    navController.popBackStack()
                }
            }
        }
    }

    private fun setData() {
        binding.apply {
            etName.setText(args.lessonData.name)
            etTeacher.setText(args.lessonData.teacher)
            etRoom.setText(args.lessonData.room)
            autoCompleteTextView.setText(args.lessonData.dayName)
            etStartTime.setText(args.lessonData.startTime)
            etEndTime.setText(args.lessonData.endTime)
        }
    }

    private fun setupObserver() {
        viewModel.editLesson.observe(viewLifecycleOwner) {
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

    private fun validate(): Boolean {
        binding.apply {
            if(etName.text.toString().isEmpty()) {
                tilName.error = getString(R.string.empty_name)
            }
            if(etTeacher.text.toString().isEmpty()) {
                tilTeacher.error = getString(R.string.empty_teacher)
            }
            if(etRoom.text.toString().isEmpty()) {
                tilRoom.error = getString(R.string.empty_room)
            }
            if(etStartTime.text.toString().isEmpty()) {
                tilStartTime.error = getString(R.string.empty_start_time)
            }
            if(etEndTime.text.toString().isEmpty()) {
                tilEndTime.error = getString(R.string.empty_end_time)
            }
            if(autoCompleteTextView.text.toString().isEmpty()) {
                tilDay.error = getString(R.string.empty_day_name)
            }
            else if(etName.text.toString().isNotEmpty() && etTeacher.text.toString().isNotEmpty()
                && etRoom.text.toString().isNotEmpty() && etStartTime.text.toString().isNotEmpty()
                && etEndTime.text.toString().isNotEmpty() && autoCompleteTextView.text.toString().isNotEmpty() ) {
                return true
            }
        }
        return false
    }
}