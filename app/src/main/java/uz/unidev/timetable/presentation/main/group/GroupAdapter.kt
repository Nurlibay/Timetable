package uz.unidev.timetable.presentation.main.group

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.GroupData
import uz.unidev.timetable.databinding.ItemGroupBinding
import uz.unidev.timetable.utils.extensions.coloredString

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 14:33
 */

class GroupAdapter : ListAdapter<GroupData, GroupAdapter.GroupViewHolder>(GroupItemCallBack) {

    var query: String? = null

    inner class GroupViewHolder(private val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.apply {
                tvGroupNumber.text = item.name.coloredString(query)
                tvCourseNumber.text = item.number.toString()
                tvSmena.text = "(${item.smena})"

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(ItemGroupBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent,false)))
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind()
    }
}

object GroupItemCallBack : DiffUtil.ItemCallback<GroupData>() {
    override fun areItemsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
        return oldItem.name == newItem.name && oldItem.number == newItem.number &&
                oldItem.smena == newItem.smena
    }

}