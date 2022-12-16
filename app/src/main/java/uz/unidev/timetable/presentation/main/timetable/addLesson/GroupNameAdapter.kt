package uz.unidev.timetable.presentation.main.timetable.addLesson

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.databinding.ItemGroupNameBinding
import uz.unidev.timetable.utils.extensions.onClick

/**
 *  Created by Nurlibay Koshkinbaev on 02/11/2022 15:31
 */

class GroupNameAdapter : ListAdapter<GroupData, GroupNameAdapter.GroupNameViewHolder>(GroupNameItemCallBack) {

    inner class GroupNameViewHolder(private val binding: ItemGroupNameBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.apply {
                checkboxGroup.text = item.name
                checkboxGroup.onClick {
                    if(checkboxGroup.isChecked){
                        itemClick.invoke(item.name)
                    }
                }
            }
        }
    }

    private var itemClick: (groupName: String) -> Unit = {}
    fun setOnItemClickListener(block: (String) -> Unit){
        itemClick = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupNameViewHolder {
        return GroupNameViewHolder(ItemGroupNameBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_group_name, parent,false)))
    }

    override fun onBindViewHolder(holder: GroupNameViewHolder, position: Int) {
        holder.bind()
    }
}

object GroupNameItemCallBack : DiffUtil.ItemCallback<GroupData>() {
    override fun areItemsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
        return oldItem.name == newItem.name && oldItem.number == newItem.number &&
                oldItem.smena == newItem.smena
    }
}