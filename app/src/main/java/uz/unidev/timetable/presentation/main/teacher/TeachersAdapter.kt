package uz.unidev.timetable.presentation.main.teacher

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.TeacherData
import uz.unidev.timetable.databinding.ItemTeacherBinding

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 14:33
 */

class TeachersAdapter : ListAdapter<TeacherData, TeachersAdapter.TeacherViewHolder>(TeacherItemCallBack) {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.apply {
                Glide
                    .with(root.context)
                    .load(item.img)
                    .placeholder(R.drawable.ic_account)
                    .centerCrop()
                    .into(ivTeacher)
                tvTeacherName.text = item.name
                tvTeacherType.text = item.type
                tvSubjectName.text = item.subject
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        return TeacherViewHolder(ItemTeacherBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_teacher, parent,false)))
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind()
    }
}

object TeacherItemCallBack : DiffUtil.ItemCallback<TeacherData>() {
    override fun areItemsTheSame(oldItem: TeacherData, newItem: TeacherData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TeacherData, newItem: TeacherData): Boolean {
        return oldItem.name == newItem.name && oldItem.subject == newItem.subject &&
                oldItem.type == newItem.type && oldItem.img == newItem.img
    }
}