package uz.unidev.timetable.presentation.main.timetable.addLesson

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
import uz.unidev.timetable.utils.ResourceState
import uz.unidev.timetable.utils.extensions.showMessage
import java.util.UUID

/**
 *  Created by Nurlibay Koshkinbaev on 27/10/2022 14:46
 */

class AddLessonScreen : Fragment(R.layout.screen_add_lesson) {

    private val binding: ScreenAddLessonBinding by viewBinding()
    private val navController by lazy { findNavController() }
    private val viewModel: AddLessonViewModel by viewModel()
    private val args: AddLessonScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
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
                        autoCompleteTextView.text.toString().lowercase()
                    )
                )
                navController.popBackStack()
            }
        }
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