package uz.unidev.timetable.presentation.main.weeks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.unidev.timetable.R
import uz.unidev.timetable.data.models.WeekData
import uz.unidev.timetable.databinding.ItemWeekBinding

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 14:33
 */

class WeekAdapter : ListAdapter<WeekData, WeekAdapter.WeekViewHolder>(WeekItemCallBack) {

    inner class WeekViewHolder(private val binding: ItemWeekBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.apply {
                tvWeekName.text = item.name
                tvStartTime.text = item.startDate + " - "
                tvEndTime.text = item.endDate

                root.setOnClickListener {
                    itemClick.invoke(item)
                }
            }
        }
    }

    private var itemClick: (weekData: WeekData) -> Unit = {}
    fun setOnItemClickListener(block: (WeekData) -> Unit){
        itemClick = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        return WeekViewHolder(ItemWeekBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_week, parent,false)))
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bind()
    }
}

object WeekItemCallBack : DiffUtil.ItemCallback<WeekData>() {
    override fun areItemsTheSame(oldItem: WeekData, newItem: WeekData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: WeekData, newItem: WeekData): Boolean {
        return oldItem.startDate == newItem.startDate && oldItem.endDate == newItem.endDate
    }
}