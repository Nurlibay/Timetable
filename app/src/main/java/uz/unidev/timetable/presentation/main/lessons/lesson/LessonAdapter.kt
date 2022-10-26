package uz.unidev.timetable.presentation.main.lessons.lesson

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.LessonData
import uz.unidev.timetable.databinding.ItemLessonBinding

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 14:33
 */

class LessonAdapter : ListAdapter<LessonData, LessonAdapter.LessonViewHolder>(LessonItemCallBack) {

    inner class LessonViewHolder(private val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.apply {
                tvStartTime.text = item.startTime
                tvEndTime.text = item.endTime
                tvRoomNumber.text = item.room
                tvTeacherName.text = item.teacher
            }
        }
    }

    private var itemClick: (lessonData: LessonData) -> Unit = {}
    fun setOnItemClickListener(block: (LessonData) -> Unit){
        itemClick = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(ItemLessonBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent,false)))
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind()
    }
}

object LessonItemCallBack : DiffUtil.ItemCallback<LessonData>() {
    override fun areItemsTheSame(oldItem: LessonData, newItem: LessonData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LessonData, newItem: LessonData): Boolean {
        return oldItem.name == newItem.name && oldItem.room == newItem.room &&
                oldItem.teacher == newItem.teacher && oldItem.startTime == newItem.startTime &&
                oldItem.endTime == newItem.endTime
    }

}