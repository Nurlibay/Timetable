package uz.unidev.timetable.presentation.main.timetable.addLesson

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.databinding.ItemGroupNameBinding

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
                root.setOnClickListener {
                    itemClick.invoke(item)
                }
            }
        }
    }

    private var itemClick: (groupData: GroupData) -> Unit = {}
    fun setOnItemClickListener(block: (GroupData) -> Unit){
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